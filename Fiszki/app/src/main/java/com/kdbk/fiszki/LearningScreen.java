package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearningScreen extends AppCompatActivity implements View.OnClickListener {
    private Button next, exit;

    NextActivity nextActivity = new NextActivity(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
        setID();

        next.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    public void onClick(View view) {
        NextActivity nextActivity = new NextActivity(this);

        switch (view.getId()) {
            case R.id.buttonNextLearning:

                break;
            case R.id.buttonExitLearning:
                nextActivity.openActivity(MainMenu.class);
                break;
        }
    }

    private void setID() {
        next = findViewById(R.id.buttonNextLearning);
        exit = findViewById(R.id.buttonExitLearning);
    }
}