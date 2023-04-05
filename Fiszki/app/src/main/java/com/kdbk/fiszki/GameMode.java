package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class GameMode extends AppCompatActivity {
    private Button quizMode, learnMode, reverse;
    private ImageView  flagFirstImage,flagSecendImage;
    private boolean isQuizModeSelected = true;
    private boolean isLearnModeSelected = false;
    private String selectedLanguage = "pl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        setID();
        setButtonListeners();

        reverse.setOnClickListener(v -> {
            if(selectedLanguage.equals("pl")) selectedLanguage = "ang";
            else if(selectedLanguage.equals("ang")) selectedLanguage = "pl";
            System.out.println(selectedLanguage);
            Drawable firstImage = flagFirstImage.getDrawable();
            Drawable secondImage = flagSecendImage.getDrawable();
            flagFirstImage.setImageDrawable(secondImage);
            flagSecendImage.setImageDrawable(firstImage);
        });
    }

    private void setID() {
        quizMode = findViewById(R.id.buttonQuizMode);
        learnMode = findViewById(R.id.buttonLearnMode);
        reverse = findViewById(R.id.buttonReverse);
        flagFirstImage = findViewById(R.id.flagFirst);
        flagSecendImage = findViewById(R.id.flagSecend);
    }

    private void setButtonListeners() {
        quizMode.setOnClickListener(v -> {
            isQuizModeSelected = !isQuizModeSelected;
            if (isQuizModeSelected) {
                quizMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
                learnMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
                isLearnModeSelected = false;
            } else {
                quizMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
            }
        });

        learnMode.setOnClickListener(v -> {
            isLearnModeSelected = !isLearnModeSelected;
            if (isLearnModeSelected) {
                learnMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
                quizMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
                isQuizModeSelected = false;
            } else {
                learnMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
            }
        });
    }
}
