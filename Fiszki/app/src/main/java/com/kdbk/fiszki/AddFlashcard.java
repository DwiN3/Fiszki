package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddFlashcard extends AppCompatActivity implements SelectListenerFlashcard {

    private NextActivity nextActivity;
    private RecyclerView mRecyclerView;
    private Button add;
    private RecyclerView.Adapter mAdapter;
    private String[] newFlashcard;
    private String word, translateWord, sampleSentence, translateSampleSentence;
    private int wordID;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelFlashcard> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);
        setID();

        list.add(new ModelFlashcard(R.drawable.arrow,"NUMER ZESTAWU","1" ,1));
        list.add(new ModelFlashcard(R.drawable.flagpl,"WPROWADŹ SŁOWO:","pies", 2));
        list.add(new ModelFlashcard(R.drawable.flagang,"DODAJ TŁUMACZENIE:","dog", 3));
        list.add(new ModelFlashcard(R.drawable.flagpl,"PRZYKŁADOWE ZDANIE","Mój pies lubi kości",4));
        list.add(new ModelFlashcard(R.drawable.flagang,"PRZYKŁADOWE ZDANIE","My dog likes bones",5));

        mRecyclerView = findViewById(R.id.addFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterFlashcard(list, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeWords();
                nextActivity.openActivity(MainMenu.class);
            }
        });
    }

    void takeWords(){
        if (!list.isEmpty()) {
            wordID = list.get(0).getID();
            word = list.get(1).getEditFlashcard();
            translateWord = list.get(2).getEditFlashcard();
            sampleSentence = list.get(3).getEditFlashcard();
            translateSampleSentence = list.get(4).getEditFlashcard();
            newFlashcard = new String[]{String.valueOf(wordID),word, translateWord, sampleSentence, translateSampleSentence};
            for(int i=0 ; i < newFlashcard.length ; i++) {
                Log.d("AddFlashcard", newFlashcard[i]); // zmiana: użyjemy Log.d() zamiast println()
            }
        }
    }

    @Override
    public void onItemClicked(ModelFlashcard modelFlashcard) {

    }

    private void setID() {
        nextActivity = new NextActivity(this);
        add = findViewById(R.id.buttonAcceptFlashcard);
    }
}
