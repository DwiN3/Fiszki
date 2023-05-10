package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.kdbk.fiszki.Other.InternetConnection;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

public class ActivityGameMode extends AppCompatActivity {

    NextActivity nextActivity = new NextActivity(this);
    private Button quizMode, learnMode, reverse, yoursKits, categories;
    private ImageView  flagFirstImage,flagSecendImage;
    private String selectedMode = "quiz";
    private String selectedLanguage = "pl";
    private InternetConnection con = new InternetConnection(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        setID();
        setButtonListeners();

        quizMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
        learnMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
    }

    private void setButtonListeners() {

            categories.setOnClickListener(v -> {
                if(!con.checkInternetConnection()) con.IntentIfNoConnection();
                else{
                    Intent intent = new Intent();
                    intent.putExtra("SelectedMode", selectedMode);
                    intent.putExtra("SelectLanguage", selectedLanguage);
                    nextActivity.openActivity(ActivityCategories.class, intent);
                }
            });

            yoursKits.setOnClickListener(v -> {
                if(!con.checkInternetConnection()) con.IntentIfNoConnection();
                else {
                    Intent intent = new Intent();
                    intent.putExtra("SelectedMode", selectedMode);
                    intent.putExtra("SelectLanguage", selectedLanguage);
                    nextActivity.openActivity(ActivityKits.class, intent);
                }
            });

            quizMode.setOnClickListener(v -> {
                if(!con.checkInternetConnection()) con.IntentIfNoConnection();
                else {
                    selectedMode = "quiz";
                    quizMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
                    learnMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
                }
            });

            learnMode.setOnClickListener(v -> {
                if(!con.checkInternetConnection()) con.IntentIfNoConnection();
                else {
                    selectedMode = "learn";
                    learnMode.setBackgroundResource(R.drawable.rounded_button_mode_pressed);
                    quizMode.setBackgroundResource(R.drawable.rounded_button_mode_normal);
                }
            });

            reverse.setOnClickListener(v -> {
                if(!con.checkInternetConnection()) con.IntentIfNoConnection();
                else{
                    if (selectedLanguage.equals("pl")) selectedLanguage = "ang";
                    else if (selectedLanguage.equals("ang")) selectedLanguage = "pl";
                    //System.out.println(selectedLanguage);
                    Drawable firstImage = flagFirstImage.getDrawable();
                    Drawable secondImage = flagSecendImage.getDrawable();
                    flagFirstImage.setImageDrawable(secondImage);
                    flagSecendImage.setImageDrawable(firstImage);
                }
            });
        }

    private void setID() {
        quizMode = findViewById(R.id.buttonQuizMode);
        learnMode = findViewById(R.id.buttonLearnMode);
        reverse = findViewById(R.id.buttonReverse);
        flagFirstImage = findViewById(R.id.flagFirst);
        flagSecendImage = findViewById(R.id.flagSecend);
        yoursKits = findViewById(R.id.buttonYourFlashcardsMode);
        categories = findViewById(R.id.buttonCategoriesMode);
    }
}
