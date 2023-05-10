package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.kdbk.fiszki.RecyclerView.Adaper.AdapterShowKitsEdit;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerShowKitsEdit;

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
        String[] words = new String[allList.size()];
        String[] translateWord = new String[allList.size()];
        String[] sentens = new String[allList.size()];
        String[] translateSentens = new String[allList.size()];


        Integer i = 0;

        for (Integer index : allList.keySet()) {
            ArrayList<ModelEditFlashcard> list = allList.get(index);
            if (!list.isEmpty()) {
                ModelEditFlashcard wordElement = list.get(0);
                words[i] = wordElement.getEditWord();
                ModelEditFlashcard translateWordElement = list.get(1);
                translateWord[i] = translateWordElement.getEditWord();
                ModelEditFlashcard sentensElement = list.get(2);
                sentens[i] = sentensElement.getEditWord();
                ModelEditFlashcard translateSentensElement = list.get(3);
                translateSentens[i] = translateSentensElement.getEditWord();
                Ids[i] = index;
                i++;
            }
        }

        for(int n=0 ; n < Ids.length ; n++){
            list.add(new ModelShowKitsEdit(words[n], translateWord[n], sentens[n], translateSentens[n],Ids[n]));
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