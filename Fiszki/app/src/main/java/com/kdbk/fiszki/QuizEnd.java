package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizEnd extends AppCompatActivity {
    NextActivity nextActivity = new NextActivity(this);
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);
        setID();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(MainMenu.class);
            }
        });

    }

    private void setID() {
        exit = findViewById(R.id.buttonBackToMenuEndQuiz);
    }
}