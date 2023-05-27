package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kdbk.fiszki.Arrays.FlashcardArray;
import com.kdbk.fiszki.Other.InternetConnection;
import com.kdbk.fiszki.Other.Token;
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
    private TextView internetError;
    private boolean entryPermissions = false;
    InternetConnection con = new InternetConnection(this);
    private Token token  = Token.getInstance();

    private boolean isBackPressedBlocked = true; // zabezpieczenie na cofania poprzez klawisz wstecz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        setID();
        if(con.checkInternetConnection()) internetError.setVisibility(View.INVISIBLE);
        else internetError.setVisibility(View.VISIBLE);

        reset.setOnClickListener(this);
        create.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(con.checkInternetConnection()){
            internetError.setVisibility(View.INVISIBLE);
            switch (view.getId()) {
                case R.id.buttonPasswordReset:
                    nextActivity.openActivity(ActivityPasswordReset.class);
                    break;
                case R.id.buttonCreate:
                    nextActivity.openActivity(ActivityRegister.class);
                    break;
                case R.id.buttonLogin:
                    checkAccount();
                    break;
            }
        }
        else internetError.setVisibility(View.VISIBLE);
    }

    private void checkAccount() {
        String loginString = String.valueOf(loginText.getText());
        String passwordString= String.valueOf(passwordText.getText());
        //String loginString = "kubiczek";
        //String passwordString= "testowehaslo";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://flashcard-app-api-bkrv.onrender.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        Login post = new Login(loginString, passwordString);
        Call<Login> call = jsonPlaceholderAPI.login(post);
        Toast.makeText(ActivityFirstScreen.this,"Trwa logowanie", Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.code() == 200){
                    Login post = response.body();
                    String TokenFromRetrofit = post.getToken();
                    token.setToken(TokenFromRetrofit);
                    token.setUserName(loginText.getText().toString());
                    nextActivity.openActivity(ActivityMainMenu.class);
                }
                if(!response.isSuccessful()){
                    Toast.makeText(ActivityFirstScreen.this,"Błędne dane", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && isBackPressedBlocked) {
            return true; // blokuj przycisk wstecz
        }
        return super.dispatchKeyEvent(event);
    }

    private void setID() {
        login = findViewById(R.id.buttonLogin);
        create = findViewById(R.id.buttonCreate);
        reset = findViewById(R.id.buttonPasswordReset);
        loginText = findViewById(R.id.textNick);
        passwordText = findViewById(R.id.textPassword);
        internetError = findViewById(R.id.idTextInternetErrorFirstScreen);
    }
}
