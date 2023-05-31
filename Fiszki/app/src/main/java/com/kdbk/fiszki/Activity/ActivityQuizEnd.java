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
    private TextView ScoreEndQuiz,userBestTrainQuiz;
    private int bestTrain=0, points =0, allWords=0;
    private boolean isBackPressedBlocked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);
        setID();

        points = gameSettingsInstance.getPoints();
        allWords = gameSettingsInstance.getAllWords();
        bestTrain = gameSettingsInstance.getBestTrain();
        ScoreEndQuiz.setText(points*10+"/"+String.valueOf(allWords*10)+" PKT");
        userBestTrainQuiz.setText(""+String.valueOf(bestTrain));

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
        ScoreEndQuiz = findViewById(R.id.textScoreEndQuiz);
        userBestTrainQuiz = findViewById(R.id.userBestTrainQuizEndText);
    }
}