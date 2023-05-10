package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

public class ActivityQuizScreen extends AppCompatActivity implements View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Button next, exit;
    private ImageView imageWordQuiz;
    private TextView answerText1, answerText2, answerText3, answerText4, nameWordQuizText, sticksLeftQuizText, scorePKT;
    private ImageButton answerButton1, answerButton2, answerButton3, answerButton4;
    private String selectedLanguage = "";
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz
    private boolean markTheAnswer = false;
    private String correctAnswer;
    private int nrWords = 3;


    // TEST QUIZU
    private int points = 0;
    private String[] NameWord = {"Pies", "Niedzwiedz", "Krowa"};
    private String[] ans1 = {"dog", "bird", "goat"};
    private String[] ans2= {"duck", "bee", "cow"};
    private String[] ans3= {"cat", "bear", "lion"};
    private String[] ans4= {"worm", "bat", "sheep"};
    private String[] correctANS = {"dog", "bear", "cow"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        setID();

        Intent intent = getIntent();
        selectedLanguage = intent.getStringExtra("SelectLanguage");

        next.setOnClickListener(this);
        exit.setOnClickListener(this);
        answerButton1.setOnClickListener(this);
        answerButton2.setOnClickListener(this);
        answerButton3.setOnClickListener(this);
        answerButton4.setOnClickListener(this);
        setQuestion(0);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    void setQuestion(int numberWord){
        markTheAnswer = false;
        scorePKT.setText(""+points);
        sticksLeftQuizText.setText(""+nrWords);
        nameWordQuizText.setText(NameWord[numberWord]);
        answerText1.setText(ans1[numberWord]);
        answerText2.setText(ans2[numberWord]);
        answerText3.setText(ans3[numberWord]);
        answerText4.setText(ans4[numberWord]);
        correctAnswer = correctANS[numberWord];
        imageWordQuiz.setBackgroundResource(R.drawable.flagpl);
    }


    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButtonAnswerQuiz1:
                if(answerText1.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=10;
                    answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton1.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                }
                break;
            case R.id.imageButtonAnswerQuiz2:
                if(answerText2.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=10;
                    answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton2.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                }
                break;
            case R.id.imageButtonAnswerQuiz3:
                if(answerText3.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=10;
                    answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton3.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText4.getText().equals(correctAnswer)) {
                        answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                }
                break;
            case R.id.imageButtonAnswerQuiz4:
                if(answerText4.getText().equals(correctAnswer) && !markTheAnswer) {
                    points +=10;
                    answerButton4.setBackgroundResource(R.drawable.rounded_button_green);
                    markTheAnswer = true;
                } else if (!markTheAnswer) {
                    answerButton4.setBackgroundResource(R.drawable.rounded_button_red);
                    if(answerText1.getText().equals(correctAnswer)) {
                        answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText2.getText().equals(correctAnswer)) {
                        answerButton2.setBackgroundResource(R.drawable.rounded_button_green);
                    } else if(answerText3.getText().equals(correctAnswer)) {
                        answerButton3.setBackgroundResource(R.drawable.rounded_button_green);
                    }
                }
                break;
            case R.id.buttonNextQuiz:
                nrWords -=1;
                if(nrWords > 0){
                    clearButtons();
                    setQuestion(nrWords);
                }
                else{
                    Intent intent = new Intent();
                    intent.putExtra("Points", String.valueOf(points));
                    nextActivity.openActivity(ActivityQuizEnd.class, intent);
                }
                break;
            case R.id.buttonExitQuiz:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    public void clearButtons(){
        answerButton1.setBackgroundResource(R.drawable.rounded_button);
        answerButton2.setBackgroundResource(R.drawable.rounded_button);
        answerButton3.setBackgroundResource(R.drawable.rounded_button);
        answerButton4.setBackgroundResource(R.drawable.rounded_button);
    }


    private void setID() {
        next = findViewById(R.id.buttonNextQuiz);
        exit = findViewById(R.id.buttonExitQuiz);
        answerButton1 = findViewById(R.id.imageButtonAnswerQuiz1);
        answerButton2 = findViewById(R.id.imageButtonAnswerQuiz2);
        answerButton3 = findViewById(R.id.imageButtonAnswerQuiz3);
        answerButton4 = findViewById(R.id.imageButtonAnswerQuiz4);
        answerText1 = findViewById(R.id.textAnswerQuiz1);
        answerText2 = findViewById(R.id.textAnswerQuiz2);
        answerText3 = findViewById(R.id.textAnswerQuiz3);
        answerText4 = findViewById(R.id.textAnswerQuiz4);
        nameWordQuizText = findViewById(R.id.nameWordQuiz);
        imageWordQuiz = findViewById(R.id.imageWordQuiz);
        sticksLeftQuizText = findViewById(R.id.sticksLeftQuiz);
        scorePKT = findViewById(R.id.userPKTScoreQuiz);
    }
}