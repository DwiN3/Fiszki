package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityQuizScreen extends AppCompatActivity implements View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Button next, exit;
    private TextView answerText1, answerText2, answerText3, answerText4;
    private ImageButton answerButton1, answerButton2, answerButton3, answerButton4;
    private String selectedLanguage = "";
    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz

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
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButtonAnswerQuiz1:
                answerButton1.setBackgroundResource(R.drawable.rounded_button_green);
                break;
            case R.id.imageButtonAnswerQuiz2:
                answerButton2.setBackgroundResource(R.drawable.rounded_button_red);
                break;
            case R.id.imageButtonAnswerQuiz3:
                answerButton3.setBackgroundResource(R.drawable.rounded_button_red);
                break;
            case R.id.imageButtonAnswerQuiz4:
                answerButton4.setBackgroundResource(R.drawable.rounded_button_red);
                break;
            case R.id.buttonNextQuiz:
                nextActivity.openActivity(ActivityQuizEnd.class);
                break;
            case R.id.buttonExitQuiz:
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
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
    }
}