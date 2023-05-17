package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

public class ActivityRegister extends AppCompatActivity {
    private Button createButton;
    private TextView emailCreateText, loginCreateText, PasswordCreateText, PasswordCreateReText;

    NextActivity nextActivity = new NextActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setID();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }

    private void CreateAccount(){


    }
    private void setID() {
        createButton = findViewById(R.id.buttonCreate);
        emailCreateText = findViewById(R.id.textEmailCreate);
        loginCreateText = findViewById(R.id.textLoginCreate);
        PasswordCreateText = findViewById(R.id.textPasswordCreate);
        PasswordCreateReText = findViewById(R.id.textPasswordCreateRe);
    }
}