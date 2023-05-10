package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.R;

public class ActivitySplashScreen extends AppCompatActivity {


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TOKEN = "123";
    private Token token  = Token.getInstance();
    private Class<?> startScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        if (token.getToken().equals("123")) {
            startScreen = ActivityMainMenu.class;
        } else {
            startScreen = ActivityFirstScreen.class;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (startScreen != null) {
                    Intent intent = new Intent(ActivitySplashScreen.this, startScreen);
                    startActivity(intent);
                } else {
                    // Handle error
                }
                finish();
            }
        }, 2500);
    }
}
