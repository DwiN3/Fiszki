package com.kdbk.fiszki.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Login {
    private String nick, password, content;

    @SerializedName("body")
    private String text;

    public Login(String nick, String password){
        this.nick = nick;
        this.password = password;
    }

    public String getNick() {
        return nick;
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
