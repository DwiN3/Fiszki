package com.kdbk.fiszki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class NextActivity {

    private final Context context;

    public NextActivity(Context context) {
        this.context = context;
    }

    public void openActivity(Class<?> toClass) {
        Intent intent = new Intent(context, toClass);
        context.startActivity(intent);
        ((Activity) context).finish();
    }
}
