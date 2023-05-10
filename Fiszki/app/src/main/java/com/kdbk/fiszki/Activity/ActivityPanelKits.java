package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kdbk.fiszki.RecyclerView.Adaper.AdapterKits;
import com.kdbk.fiszki.Arrays.KitsArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelKits;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerKits;

import java.util.ArrayList;

public class ActivityPanelKits extends AppCompatActivity implements SelectListenerKits, View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);

    private Button edit, del, menu;
    private TextView numberKit, timesPlayed;
    private int ID = 1, playedGames;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private KitsArray kitsArray = KitsArray.getInstance();
    private ArrayList<ModelKits> list = kitsArray.getList();
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_kits);
        setID();

        edit.setOnClickListener(this);
        del.setOnClickListener(this);
        menu.setOnClickListener(this);

        if (!list.isEmpty()) {
            numberKit.setText("Zestaw "+list.get(0).getID());
            timesPlayed.setText(list.get(0).getGamesPlayed()+" razy");
        } else {
            numberKit.setText("Brak dostępnych zestawów");
        }

        RefreshRecycleView();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    private void RefreshRecycleView(){
        mRecyclerView = findViewById(R.id.kitsPanelRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new AdapterKits(list, this, R.layout.recycler_view_kits_small);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonEditKitPanel:
                nextActivity.openActivity(ActivityShowKitsEdit.class);

                break;
            case R.id.buttonDeleteKitPanel:
                ModelKits modelKits = list.stream()
                        .filter(m -> m.getID() == ID)
                        .findFirst()
                        .orElse(null);
                if (modelKits != null) {
                    list.remove(modelKits);
                }
                RefreshRecycleView();
                resetAfterDelate();
                break;
            case R.id.buttonBackToMenuPanel:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    public void resetAfterDelate(){
        if (list.isEmpty()) {
            numberKit.setText("Brak dostępnych zestawów");
            timesPlayed.setText("");
        } else {
            numberKit.setText("Zestaw " + list.get(0).getID());
            timesPlayed.setText(list.get(0).getGamesPlayed() + " razy");
            ID = list.get(0).getID();
        }
    }

    @Override
    public void onItemClicked(ModelKits modelKits) {
        ID = modelKits.getID();
        playedGames = modelKits.getGamesPlayed();
        timesPlayed.setText(playedGames+" razy");
        numberKit.setText("Zestaw "+ID);
    }

    private void setID() {
        edit = findViewById(R.id.buttonEditKitPanel);
        del = findViewById(R.id.buttonDeleteKitPanel);
        menu = findViewById(R.id.buttonBackToMenuPanel);
        numberKit = findViewById(R.id.textNumberKitPanel);
        timesPlayed = findViewById(R.id.textTimesEndQuiz);
    }
}