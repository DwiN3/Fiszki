package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    private Button logout, learn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setID();

        logout.setOnClickListener(this);
        learn.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogout:
                openActivity(FirstScreen.class);
                break;
            case R.id.buttonLEARN:
                openActivity(GameMode.class);
                break;
        }
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void setID() {
        logout = findViewById(R.id.buttonLogout);
        learn = findViewById(R.id.buttonLEARN);
    }
}