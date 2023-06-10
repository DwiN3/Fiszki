package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kdbk.fiszki.Mail.SendEmailTLS;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

import java.io.IOException;

public class ActivityPasswordReminder extends AppCompatActivity {
    private NextActivity nextActivity = new NextActivity(this);
    private EditText email;
    private Button confirmRemind, passwordReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reminder);
        setID();

        confirmRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString = String.valueOf(email.getText());
                String subject = "Przypomnienie hasła w aplikacji fiszki";
                String message = "Nazwa:   "+ "nick" +"\nHasło:     "+ "haslo";;
                SendEmailTLS sendEmailTLS = null;
                try {
                    sendEmailTLS = new SendEmailTLS(emailString, subject, message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        passwordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity.openActivity(ActivityPasswordReset.class);
            }
        });
    }

    private void setID() {
        confirmRemind = findViewById(R.id.buttonPasswordConfirmReminder);
        passwordReset = findViewById(R.id.buttonPasswordResetReminder);
        email = findViewById(R.id.textEmailReminder);
    }
}
