package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

public class ActivityQuizEnd extends AppCompatActivity {
    NextActivity nextActivity = new NextActivity(this);
    private Button exit;
    private String points ="";
    private TextView pointsText;

    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);
        setID();

        Intent intent = getIntent();
        points = intent.getStringExtra("Points");
        pointsText.setText(points);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityMainMenu.class);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        exit = findViewById(R.id.buttonBackToMenuEndQuiz);
        pointsText = findViewById(R.id.textScorePKTEndQuiz);
    }
}