package com.kdbk.fiszki.Other;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnection {

    private Context context;
    private Class<?> redirectActivityClass;

    public InternetConnection(Context context) {
        this.context = context;
    }

    public InternetConnection(Context context, Class<?> redirectActivityClass) {
        this.context = context;
        this.redirectActivityClass = redirectActivityClass;
    }

    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void checkAndRedirectIfNoConnection() {
        if (!checkInternetConnection()) {
            Intent intent = new Intent(context, redirectActivityClass);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
}
