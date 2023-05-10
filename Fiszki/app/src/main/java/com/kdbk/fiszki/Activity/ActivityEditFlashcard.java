package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.kdbk.fiszki.RecyclerView.Adaper.AdapterEditFlashcard;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerEditFlashcard;

import java.util.ArrayList;

public class ActivityEditFlashcard extends AppCompatActivity implements SelectListenerEditFlashcard, AdapterEditFlashcard.OnEditTextChangeListener {

    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private int nrWord, lastWords;
    Button accept, back, delete;
    private String[] words;
    String word, translateWord,sentens,sentensTranslate;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        Intent intent = getIntent();
        nrWord = intent.getIntExtra("NrWordID", 1);
        EditFlashcardArray instance = EditFlashcardArray.getInstance();
        ArrayList<ModelEditFlashcard> list = instance.getList(nrWord);

        ModelEditFlashcard wordElement = list.get(0);
        word = wordElement.getEditWord();
        ModelEditFlashcard editTranslateWordElement = list.get(1);
        translateWord = editTranslateWordElement.getEditWord();
        ModelEditFlashcard sentensElement = list.get(2);
        sentens = sentensElement.getEditWord();
        ModelEditFlashcard sentensTranslateElement = list.get(3);
        sentensTranslate = sentensTranslateElement.getEditWord();

        mRecyclerView = findViewById(R.id.editFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AdapterEditFlashcard(list, this, this);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ModelEditFlashcard> list = instance.getList(nrWord);
                ModelEditFlashcard wordElement = list.get(0);
                wordElement.setEditWord(word);
                ModelEditFlashcard editTranslateWordElement = list.get(1);
                editTranslateWordElement.setEditWord(translateWord);
                ModelEditFlashcard sentensElement = list.get(2);
                sentensElement.setEditWord(sentens);
                ModelEditFlashcard sentensTranslateElement = list.get(3);
                sentensTranslateElement.setEditWord(sentensTranslate);

                String tabwords[] = {word,translateWord, sentens,sentensTranslate};
                for(int n=0;n<tabwords.length;n++) System.out.println(tabwords[n]);
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFlashcardArray instance = EditFlashcardArray.getInstance();
                instance.remove(nrWord);
                instance.getWords();
                mAdapter.notifyDataSetChanged();
                //System.out.println(instance.getWords());
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
    }

    void takeWords() {
        //words = new String[]{"word", "translateWord", "sampleSentence", "translateSampleSentence"};

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
                sentens = newText;
                break;
            case 4:
                sentensTranslate = newText;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstęcz
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        accept = findViewById(R.id.buttonEditFlashCardAccept);
        back = findViewById(R.id.buttonEditFlashCardBack);
        delete = findViewById(R.id.buttonEditFlashCardDelate);
    }
}
