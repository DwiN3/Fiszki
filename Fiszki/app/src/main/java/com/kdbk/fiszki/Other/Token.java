package com.kdbk.fiszki.Other;

public class Token {
    private static Token instance = null;
    private static String userName ="", token="";

    public static Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

    public void setUserName(String userName) {
        Token.userName = userName;
    }

    public void setToken(String token) {
        Token.token = token;
    }
}
