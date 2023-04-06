package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class YourProfile extends AppCompatActivity {

    NextActivity nextActivity = new NextActivity(this);
    private Button yoursKitsPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);
        setID();

        yoursKitsPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(PanelKits.class);
            }
        });
    }

    private void setID() {
        yoursKitsPanel = findViewById(R.id.buttonYourFlashcardsProfile);
    }
}