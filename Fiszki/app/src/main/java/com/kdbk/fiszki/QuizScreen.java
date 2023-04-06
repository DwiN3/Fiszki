package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class QuizScreen extends AppCompatActivity implements View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Button exit;
    private ImageButton answer1,answer2, answer3, answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        setID();

        exit.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButtonAnswerQuiz1:
                break;
            case R.id.imageButtonAnswerQuiz2:
                break;
            case R.id.imageButtonAnswerQuiz3:
                break;
            case R.id.imageButtonAnswerQuiz4:
                break;
            case R.id.buttonExitQuiz:
                nextActivity.openActivity(MainMenu.class);
                break;
        }
    }

    private void setID() {
        exit = findViewById(R.id.buttonExitQuiz);
        answer1 = findViewById(R.id.imageButtonAnswerQuiz1);
        answer2 = findViewById(R.id.imageButtonAnswerQuiz2);
        answer3 = findViewById(R.id.imageButtonAnswerQuiz3);
        answer4 = findViewById(R.id.imageButtonAnswerQuiz4);
    }
}