package com.kdbk.fiszki.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.kdbk.fiszki.Other.NextActivity;
import com.kdbk.fiszki.R;

public class ActivityPasswordReset extends AppCompatActivity {

    private NextActivity nextActivity = new NextActivity(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
    }
}