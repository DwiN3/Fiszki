package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    private Button logout, learn, yourProfile;

    NextActivity nextActivity = new NextActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setID();

        logout.setOnClickListener(this);
        learn.setOnClickListener(this);
        yourProfile.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonLogout:
                nextActivity.openActivity(FirstScreen.class);
                break;
            case R.id.buttonLEARN:
                nextActivity.openActivity(GameMode.class);
                break;
            case R.id.buttonYourProfile:
                nextActivity.openActivity(YourProfile.class);
                break;
        }
    }

    private void setID() {
        logout = findViewById(R.id.buttonLogout);
        learn = findViewById(R.id.buttonLEARN);
        yourProfile = findViewById(R.id.buttonYourProfile);
    }
}