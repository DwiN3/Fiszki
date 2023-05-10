package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kdbk.fiszki.RecyclerView.Adaper.AdapterAddFlashcard;
import com.kdbk.fiszki.Arrays.EditFlashcardArray;
import com.kdbk.fiszki.Arrays.FlashcardArray;
import com.kdbk.fiszki.RecyclerView.Model.ModelAddFlashcard;
import com.kdbk.fiszki.RecyclerView.Model.ModelEditFlashcard;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Arrays.WordsArray;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.SelectListener.SelectListenerAddFlashcard;

import java.util.ArrayList;

public class ActivityAddFlashcard extends AppCompatActivity implements SelectListenerAddFlashcard, AdapterAddFlashcard.OnEditTextChangeListener {


    private NextActivity nextActivity = new NextActivity(this);
    private RecyclerView mRecyclerView;
    private Button add;
    private RecyclerView.Adapter mAdapter;
    private String[] newFlashcard;
    private String nrKit, word, translateWord, sampleSentence, translateSampleSentence;
    private RecyclerView.LayoutManager mLayoutManager;

    private FlashcardArray flashcardArray = FlashcardArray.getInstance();
    private ArrayList<ModelAddFlashcard> list = flashcardArray.getList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);
        setID();

        mRecyclerView = findViewById(R.id.addFlashcardlRecycleView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterAddFlashcard(list, this, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        takeWords();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFlashcardArray editFlashcardArray = EditFlashcardArray.getInstance();
                int nextIndex = editFlashcardArray.getWords() + 1;
                ArrayList<ModelEditFlashcard> subList = new ArrayList<>();
                subList.add(new ModelEditFlashcard(R.drawable.flagpl, word,1, nextIndex));
                subList.add(new ModelEditFlashcard(R.drawable.flagang, translateWord, 2, nextIndex));
                subList.add(new ModelEditFlashcard(R.drawable.flagpl, sampleSentence, 3, nextIndex));
                subList.add(new ModelEditFlashcard(R.drawable.flagang, translateSampleSentence, 4, nextIndex));
                editFlashcardArray.getAllList().put(nextIndex, subList);
                newFlashcard = new String[]{nrKit, word, translateWord, sampleSentence, translateSampleSentence};
                WordsArray wordsArray = new WordsArray();
                wordsArray.addWord(newFlashcard);
                for (int i = 0; i < newFlashcard.length; i++) {
                    Log.d("AddFlashcard", newFlashcard[i]);
                }
                nextActivity.openActivity(ActivityMainMenu.class);
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
    public void onItemClicked(ModelAddFlashcard modelAddFlashcard) {

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