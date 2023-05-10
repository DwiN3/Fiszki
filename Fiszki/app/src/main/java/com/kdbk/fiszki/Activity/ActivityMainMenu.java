package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kdbk.fiszki.Other.InternetConnection;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.R;

public class ActivityMainMenu extends AppCompatActivity implements View.OnClickListener {
    NextActivity nextActivity = new NextActivity(this);
    private Button logout, learn, yourProfile, addFlashcards;
    private TextView helloNick, internetError;
    private boolean kits = false;
    private Token token  = Token.getInstance();

    InternetConnection con = new InternetConnection(this);
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LASTUSERNAME = "lastusername";
    public static final String LASTTOKEN = "lasttoken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setID();

        helloNick.setText("Witaj "+token.getUserName());
        saveData();
        logout.setOnClickListener(this);
        learn.setOnClickListener(this);
        yourProfile.setOnClickListener(this);
        addFlashcards.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(con.checkInternetConnection()){
            internetError.setVisibility(View.INVISIBLE);
            switch (view.getId()) {
                case R.id.buttonLEARN:
                    nextActivity.openActivity(ActivityGameMode.class);
                    break;
                case R.id.buttonYourProfile:
                    nextActivity.openActivity(ActivityYourProfile.class);
                    break;
                case R.id.buttonAddFlashcard:
                    kits = true;
                    Intent intent = getIntent();
                    intent.putExtra("KitsToShow", kits);
                    nextActivity.openActivity(ActivityAddFlashcard.class, intent);
                    break;
            }
        }
        else internetError.setVisibility(View.VISIBLE);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token.setUserName("");
                token.setToken("");
                saveData();
                nextActivity.openActivity(ActivityFirstScreen.class);
            }
        });
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LASTUSERNAME, token.getUserName());
        editor.putString(LASTTOKEN, token.getToken());
        editor.apply();
    }

    private void setID() {
        logout = findViewById(R.id.buttonLogout);
        learn = findViewById(R.id.buttonLEARN);
        yourProfile = findViewById(R.id.buttonYourProfile);
        addFlashcards = findViewById(R.id.buttonAddFlashcard);
        helloNick = findViewById(R.id.textHelloNick);
        internetError = findViewById(R.id.idTextInternetError);
    }
}