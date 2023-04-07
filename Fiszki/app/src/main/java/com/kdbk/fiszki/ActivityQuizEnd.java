package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class ActivityQuizEnd extends AppCompatActivity {
    NextActivity nextActivity = new NextActivity(this);
    private Button exit;

    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);
        setID();

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
    }
}