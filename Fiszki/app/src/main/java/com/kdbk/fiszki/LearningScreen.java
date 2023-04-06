package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LearningScreen extends AppCompatActivity {

    NextActivity nextActivity = new NextActivity(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
    }
}