package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class GameMode extends AppCompatActivity {
    private Button quizMode, learnMode, reverse, yoursKits, gameMode;
    private ImageView  flagFirstImage,flagSecendImage;
    private String selectedMode = "quiz";
    private String selectedLanguage = "pl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        setID();
        setButtonListeners();

        quizMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
        learnMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
    }

    private void setID() {
        quizMode = findViewById(R.id.buttonQuizMode);
        learnMode = findViewById(R.id.buttonLearnMode);
        reverse = findViewById(R.id.buttonReverse);
        flagFirstImage = findViewById(R.id.flagFirst);
        flagSecendImage = findViewById(R.id.flagSecend);
        yoursKits = findViewById(R.id.buttonYourFlashcardsMode);
        gameMode = findViewById(R.id.buttonCategoriesMode);
    }

    private void setButtonListeners() {
        gameMode.setOnClickListener(v -> {
            openActivity(Categories.class);
        });

        yoursKits.setOnClickListener(v -> {
            openActivity(YourKits.class);
        });

        quizMode.setOnClickListener(v -> {
            selectedMode = "quiz";
            quizMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
            learnMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
        });

        learnMode.setOnClickListener(v -> {
            selectedMode = "learn";
            learnMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
            quizMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
        });

        reverse.setOnClickListener(v -> {
            if(selectedLanguage.equals("pl")) selectedLanguage = "ang";
            else if(selectedLanguage.equals("ang")) selectedLanguage = "pl";
            //System.out.println(selectedLanguage);
            Drawable firstImage = flagFirstImage.getDrawable();
            Drawable secondImage = flagSecendImage.getDrawable();
            flagFirstImage.setImageDrawable(secondImage);
            flagSecendImage.setImageDrawable(firstImage);
        });
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
