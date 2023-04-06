package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class EditFlashcard extends AppCompatActivity implements SelectListenerEditFlashcard, AdapterEditFlashcard.OnEditTextChangeListener {


    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private String[] newFlashcard;
    private String  word, translateWord, sampleSentence, translateSampleSentence;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditFlashcardArray flashcardArray = com.kdbk.fiszki.EditFlashcardArray.getInstance();
    private ArrayList<ModelEditFlashcard> list = flashcardArray.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);

        mRecyclerView = findViewById(R.id.editFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterEditFlashcard(list, this, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

    }

    void takeWords() {
        if (!list.isEmpty()) {
            word = list.get(0).getEditWord();
            translateWord = list.get(1).getEditWord();
            sampleSentence = list.get(2).getEditWord();
            translateSampleSentence = list.get(3).getEditWord();
        }
    }

    @Override
    public void onItemClicked(ModelEditFlashcard modelEditFlashcard) {

    }

    @Override
    public void onEditTextChanged(int cardId, String newText) {
        switch (cardId) {
            case 1:
                word = newText;
                break;
            case 2:
                translateWord = newText;
                break;
            case 3:
                sampleSentence = newText;
                break;
            case 4:
                translateSampleSentence = newText;
                break;
            default:
                break;
        }
    }
}