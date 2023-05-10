package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kdbk.fiszki.R;

public class ActivitySplashScreen extends AppCompatActivity {

    private static final String TOKEN = "123123";
    private Class<?> startScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (TOKEN.equals("123123")) {
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
