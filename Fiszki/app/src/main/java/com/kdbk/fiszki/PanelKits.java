package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PanelKits extends AppCompatActivity implements SelectListenerKits, View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);

    private Button edit, del, menu;
    private TextView numberKit, timesPlayed;
    private int ID = 1, playedGames;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelKits> modelKitsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_kits);
        setID();

        edit.setOnClickListener(this);
        del.setOnClickListener(this);
        menu.setOnClickListener(this);

        modelKitsArray.add(new ModelKits("Zestaw 1","ILOSC FISZEK", "30", 1, 5));
        modelKitsArray.add(new ModelKits("Zestaw 2","ILOSC FISZEK", "18", 2, 31));
        modelKitsArray.add(new ModelKits("Zestaw 3","ILOSC FISZEK", "25", 3,21));
        modelKitsArray.add(new ModelKits("Zestaw 4","ILOSC FISZEK", "30", 4,3));
        modelKitsArray.add(new ModelKits("Zestaw 5","ILOSC FISZEK", "11", 5,15));

        numberKit.setText("Zestaw "+modelKitsArray.get(0).getID());
        timesPlayed.setText(modelKitsArray.get(0).getGamesPlayed()+" razy");
        RefreshRecycleView();
    }

    private void RefreshRecycleView(){
        mRecyclerView = findViewById(R.id.kitsPanelRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new AdapterKit(modelKitsArray, this, R.layout.recycler_view_kits_small);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonEditKitPanel:

                break;
            case R.id.buttonDeleteKitPanel:
                ModelKits modelKit = modelKitsArray.stream()
                        .filter(m -> m.getID() == ID)
                        .findFirst()
                        .orElse(null);
                if (modelKit != null) {
                    modelKitsArray.remove(modelKit);
                }
                RefreshRecycleView();
                resetAfterDelate();
                break;
            case R.id.buttonBackToMenuPanel:
                nextActivity.openActivity(MainMenu.class);
                break;
        }
    }

    public void resetAfterDelate(){
        if (modelKitsArray.isEmpty()) {
            numberKit.setText("Brak dostępnych zestawów");
            timesPlayed.setText("");
        } else {
            // lista zawiera co najmniej jeden element
            numberKit.setText("Zestaw " + modelKitsArray.get(0).getID());
            timesPlayed.setText(modelKitsArray.get(0).getGamesPlayed() + " razy");
        }
    }

    @Override
    public void onItemClicked(ModelKits modelKit) {
        ID = modelKit.getID();
        playedGames = modelKit.getGamesPlayed();
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