package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityEditFlashcard extends AppCompatActivity implements SelectListenerEditFlashcard, AdapterEditFlashcard.OnEditTextChangeListener {

    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    Button accept, back, delate;
    private String[] editedFlashcard;
    private String  word, translateWord, sampleSentence, translateSampleSentence;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditFlashcardArray flashcardArray = com.kdbk.fiszki.EditFlashcardArray.getInstance();
    private ArrayList<ModelEditFlashcard> list = flashcardArray.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        mRecyclerView = findViewById(R.id.editFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterEditFlashcard(list, this, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editedFlashcard = new String[]{word, translateWord, sampleSentence, translateSampleSentence};
                for (int i = 0; i < editedFlashcard.length; i++) {
                    Log.d("EditedFlashcard", editedFlashcard[i]);
                }
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });

        delate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        accept = findViewById(R.id.buttonEditFlashCardAccept);
        back = findViewById(R.id.buttonEditFlashCardBack);
        delate = findViewById(R.id.buttonEditFlashCardDelate);
    }
}