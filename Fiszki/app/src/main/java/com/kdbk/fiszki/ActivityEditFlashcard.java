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
    Button accept, back, delete;
    private String[] editedFlashcard;
    private String[] words;
    private ArrayList<ModelEditFlashcard> list = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

        EditFlashcardArray instance = EditFlashcardArray.getInstance();
        ArrayList<ModelEditFlashcard> list1 = instance.getList(1);

        ModelEditFlashcard firstElement = list1.get(0);

        ModelEditFlashcard SecElement = list1.get(1);
        int imageResource = firstElement.getImageResource();
        String editWord = firstElement.getEditWord();
        int cardId = firstElement.getCardId();
        int nrWord = firstElement.getNrWord();

        System.out.println(editWord);

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
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.remove(0);
                }
                mAdapter.notifyDataSetChanged();
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
        ModelEditFlashcard model = list.get(cardId - 1);
        switch (cardId) {

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
