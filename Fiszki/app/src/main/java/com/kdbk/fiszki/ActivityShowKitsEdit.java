package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Map;

public class ActivityShowKitsEdit extends AppCompatActivity implements SelectListenerShowKitsEdit {

    NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String selectedMode = "";
    private String selectedLanguage = "";
    private ArrayList<ModelShowKitsEdit> list = new ArrayList<>();
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_kits_edit);
        setID();

        EditFlashcardArray editFlashcardArray = EditFlashcardArray.getInstance();
        Map<Integer, ArrayList<ModelEditFlashcard>> allList = editFlashcardArray.getAllList();

        Integer[] Ids = new Integer[allList.size()];

        Integer i = 0;

        for (Integer index : allList.keySet()) {
            ArrayList<ModelEditFlashcard> list = allList.get(index);
            if (!list.isEmpty()) {
                Ids[i] = index;
                i++;
            }
        }

        for(int n=0 ; n < Ids.length ; n++){
            list.add(new ModelShowKitsEdit(Ids[n]));
        }


        mRecyclerView = findViewById(R.id.showWordKitsRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterShowKitsEdit(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityPanelKits.class);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onItemClicked(ModelShowKitsEdit modelShowKitsEdit) {
        Intent intent = getIntent();
        //intent.putExtra("LastWords", lastWords);
        intent.putExtra("NrWordID", modelShowKitsEdit.getID());
        nextActivity.openActivity(ActivityEditFlashcard.class, intent);
    }

    private void setID() {
        back = findViewById(R.id.buttonBackShowKits);
    }
}