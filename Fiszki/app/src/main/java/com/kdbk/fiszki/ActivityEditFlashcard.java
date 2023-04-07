package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    private int nrWord, lastWords;
    private EditFlashcardArray flashcardArray;
    private ArrayList<ModelEditFlashcard> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        Intent intent = getIntent();
        nrWord = intent.getIntExtra("NrWordID", 2);
        flashcardArray = com.kdbk.fiszki.EditFlashcardArray.getInstance(nrWord);
        list = new ArrayList<>();
        list.addAll(flashcardArray.getList(nrWord));
        System.out.println(nrWord);

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
                for(int n = 0 ; n < editedFlashcard.length ; n++) System.out.println(editedFlashcard[n]);
                nextActivity.openActivity(ActivityShowKitsEdit.class);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityShowKitsEdit.class);
                flashcardArray.setList(list, nrWord);
                Intent intent = getIntent();
                intent.putExtra("LastWords", flashcardArray.getWord());
                nextActivity.openActivity(ActivityShowKitsEdit.class, intent);
            }
        });

        delate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getNrWord() == nrWord) {
                            list.remove(i);
                            break;
                        }
                    }
                    flashcardArray.setList(list, nrWord);
                    flashcardArray.delateWord();
                    System.out.println("usunięto liste "+ nrWord+1);
                    Intent intent = getIntent();
                    intent.putExtra("LastWords", flashcardArray.getWord());
                    nextActivity.openActivity(ActivityShowKitsEdit.class, intent);
                }
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
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        accept = findViewById(R.id.buttonEditFlashCardAccept);
        back = findViewById(R.id.buttonEditFlashCardBack);
        delate = findViewById(R.id.buttonEditFlashCardDelate);
    }
}