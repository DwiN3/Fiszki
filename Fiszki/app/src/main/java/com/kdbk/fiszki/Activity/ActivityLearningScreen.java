package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Other.SetGameClass;
import com.kdbk.fiszki.R;

public class ActivityLearningScreen extends AppCompatActivity implements View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Button next, exit;
    private String selectedLanguage = "", selectedID="", selectedData="";
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private TextView nameWord, sticksLeft, textsampleSentence;
    private Button buttonNext;
    private ImageView imageWord;
    private int nrWords, allWords, countWords = 0;
    SetGameClass t = new SetGameClass("category");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
        setID();

        Intent intent = getIntent();
        selectedLanguage = intent.getStringExtra("SelectLanguage");
        selectedID = intent.getStringExtra("SelectID");
        selectedData = intent.getStringExtra("SelectData");
        t =  new SetGameClass(selectedData);

        allWords = t.getWords();
        nrWords = t.getWords()-1;
        setNewWord(nrWords);

        next.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonNextLearning:
                if(buttonNext.getText().equals("POKAŻ TŁUMACZENIE")){
                    buttonNext.setText("NASTĘPNE SŁOWO");
                    translateWord(nrWords);
                    if (nrWords == 0) {
                        buttonNext.setVisibility(View.GONE);
                        buttonNext.setEnabled(false);
                    }
                }
                else
                {
                    buttonNext.setText("POKAŻ TŁUMACZENIE");
                    if(nrWords > 0) {
                        nrWords -= 1;
                        setNewWord(nrWords);
                    }
                }
                break;
            case R.id.buttonExitLearning:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    private void setNewWord(int numberWord){
        nameWord.setText(t.getNameWord(numberWord));
        textsampleSentence.setText(t.getSentense(numberWord));
        imageWord.setBackgroundResource(t.getImg(numberWord));
        countWords +=1;
        sticksLeft.setText(countWords+"/"+allWords);
    }

    private void translateWord(int numberWord){
        nameWord.setText(t.getCorrectANS(numberWord));
        textsampleSentence.setText(t.getSentenseTra(numberWord));
    }

    private void setID() {
        next = findViewById(R.id.buttonNextLearning);
        exit = findViewById(R.id.buttonExitLearning);
        nameWord = findViewById(R.id.textnameWordLearning);
        sticksLeft = findViewById(R.id.textsticksLeftLearning);
        textsampleSentence = findViewById(R.id.textsampleSentenceLearning);
        imageWord = findViewById(R.id.imageWordLearning);
        buttonNext = findViewById(R.id.buttonNextLearning);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }
}