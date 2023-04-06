package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddFlashcard extends AppCompatActivity implements SelectListenerFlashcard, AdapterFlashcard.OnEditTextChangeListener {


    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private Button add;
    private RecyclerView.Adapter mAdapter;
    private String[] newFlashcard;
    private String nrKit, word, translateWord, sampleSentence, translateSampleSentence;
    private RecyclerView.LayoutManager mLayoutManager;

    private FlashcardArray flashcardArray = com.kdbk.fiszki.FlashcardArray.getInstance();
    private ArrayList<ModelFlashcard> list = flashcardArray.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);
        setID();

        mRecyclerView = findViewById(R.id.addFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterFlashcard(list, this, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFlashcard = new String[]{nrKit, word, translateWord, sampleSentence, translateSampleSentence};
                for (int i = 0; i < newFlashcard.length; i++) {
                    Log.d("AddFlashcard", newFlashcard[i]);
                }
                nextActivity.openActivity(MainMenu.class);
            }
        });
    }

    void takeWords() {
        if (!list.isEmpty()) {
            nrKit = String.valueOf(list.get(0).getcardId());
            word = list.get(1).getEditFlashcard();
            translateWord = list.get(2).getEditFlashcard();
            sampleSentence = list.get(3).getEditFlashcard();
            translateSampleSentence = list.get(4).getEditFlashcard();
        }
    }

    @Override
    public void onItemClicked(ModelFlashcard modelFlashcard) {

    }

    private void setID() {
        add = findViewById(R.id.buttonAcceptFlashcard);
    }

    @Override
    public void onEditTextChanged(int cardId, String newText) {
        switch (cardId) {
            case 1:
                nrKit = newText;
                break;
            case 2:
                word = newText;
                break;
            case 3:
                translateWord = newText;
                break;
            case 4:
                sampleSentence = newText;
                break;
            case 5:
                translateSampleSentence = newText;
                break;
            default:
                break;
        }
    }
}