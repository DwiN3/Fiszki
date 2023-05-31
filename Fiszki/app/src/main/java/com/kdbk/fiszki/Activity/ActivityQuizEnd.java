package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kdbk.fiszki.Instance.GameSettingsInstance;
import com.kdbk.fiszki.Instance.TokenInstance;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

public class ActivityQuizEnd extends AppCompatActivity {
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
    private NextActivity nextActivity = new NextActivity(this);
    private Button exit;
    private TextView pointsText, ScoreEndQuiz;
    private String points ="", allWords="";
    private boolean isBackPressedBlocked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);
        setID();

        points = String.valueOf(gameSettingsInstance.getPoints());
        allWords = String.valueOf(gameSettingsInstance.getAllWords());
        pointsText.setText(points);
        ScoreEndQuiz.setText("/"+allWords+" PKT");

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
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        exit = findViewById(R.id.buttonBackToMenuEndQuiz);
        pointsText = findViewById(R.id.textScorePKTEndQuiz);
        ScoreEndQuiz = findViewById(R.id.textScoreEndQuiz);
    }
}