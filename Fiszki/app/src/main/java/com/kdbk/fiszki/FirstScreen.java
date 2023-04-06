package com.kdbk.fiszki;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity implements View.OnClickListener {

    NextActivity nextActivity = new NextActivity(this);
    private Button login, create, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        setID();

        reset.setOnClickListener(this);
        create.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonPasswordReset:
                nextActivity.openActivity(PasswordReset.class);
                break;
            case R.id.buttonCreate:
                nextActivity.openActivity(Register.class);
                break;
            case R.id.buttonLogin:
                nextActivity.openActivity(MainMenu.class);
                break;
        }
    }

    private void setID() {
        login = findViewById(R.id.buttonLogin);
        create = findViewById(R.id.buttonCreate);
        reset = findViewById(R.id.buttonPasswordReset);
    }
}
