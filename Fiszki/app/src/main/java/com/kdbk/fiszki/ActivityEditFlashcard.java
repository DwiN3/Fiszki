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

    private EditFlashcardArray flashcardArray = com.kdbk.fiszki.EditFlashcardArray.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flashcard);
        setID();

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
                for (int i = 0; i < list.size(); i++) {
                    ModelEditFlashcard model = list.get(i);
                    Log.d("EditedFlashcard", model.getEditWord() + ", " + model.getEditTranslation() + ", " + model.getEditSampleSentence() + ", " + model.getEditTranslationSampleSentence());
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
        words = new String[]{"word", "translateWord", "sampleSentence", "translateSampleSentence"};
        for (String word : words) {
            ModelEditFlashcard model = new ModelEditFlashcard(word, "", "", "");
            list.add(model);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(ModelEditFlashcard modelEditFlashcard) {

    }

    @Override
    public void onEditTextChanged(int cardId, String newText) {
        ModelEditFlashcard model = list.get(cardId - 1);
        switch (cardId) {
            case 1:
                model.setEditWord(newText);
                break;
            case 2:
                model.setEditTranslation(newText);
                break;
            case 3:
                model.setEditSampleSentence(newText);
                break;
            case 4:
                model.setEditTranslationSampleSentence(newText);
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = list.size();
                for (int i = size - 1; i >= 0; i--) {
                    ModelEditFlashcard model = list.get(i);
                    if (model.isSelected()) {
                        list.remove(model);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
