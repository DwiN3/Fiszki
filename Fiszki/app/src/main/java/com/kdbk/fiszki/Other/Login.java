package com.kdbk.fiszki.Other;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Login {
    private String login, password, content;

    @SerializedName("body")
    private String text;

    public Login(String login, String password, String content){
        this.login = login;
        this.password = password;
        this.content = content;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getContent() {
        return content;
    }

    public String getText() {
        return text;
    }
}
