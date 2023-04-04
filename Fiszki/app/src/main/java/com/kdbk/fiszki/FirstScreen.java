package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity {

    private Button login,create,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        setID();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPasswordReset();
            }
        });
    }

    public void openPasswordReset(){
        Intent intent = new Intent(this, PasswordReset.class);
        startActivity(intent);
    }

    public void setID() {
        login = findViewById(R.id.buttonLogin);
        create = findViewById(R.id.buttonCreate);
        reset = findViewById(R.id.buttonPasswordReset);
    }
}