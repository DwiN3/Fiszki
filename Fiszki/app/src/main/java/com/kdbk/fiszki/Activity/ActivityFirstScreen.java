package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kdbk.fiszki.Retrofit.JsonPlaceholderAPI;
import com.kdbk.fiszki.Retrofit.Login;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityFirstScreen extends AppCompatActivity implements View.OnClickListener {

    NextActivity nextActivity = new NextActivity(this);
    private Button login, create, reset;
    private EditText loginText, passwordText;

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
                nextActivity.openActivity(ActivityPasswordReset.class);
                break;
            case R.id.buttonCreate:
                nextActivity.openActivity(ActivityRegister.class);
                break;
            case R.id.buttonLogin:
                checkAccount();
                nextActivity.openActivity(ActivityMainMenu.class);
                break;
        }
    }

    private void setID() {
        login = findViewById(R.id.buttonLogin);
        create = findViewById(R.id.buttonCreate);
        reset = findViewById(R.id.buttonPasswordReset);
        loginText = findViewById(R.id.textNick);
        passwordText = findViewById(R.id.textPassword);
    }

    private void checkAccount() {
        String loginString = String.valueOf(loginText.getText());
        String passwordString= String.valueOf(passwordText.getText());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        Login post = new Login(loginString, passwordString);
        Call<Login> call = jsonPlaceholderAPI.login(post);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
            }
        });
    }
}