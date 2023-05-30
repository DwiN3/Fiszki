package com.kdbk.fiszki.Retrofit.Models;

import com.google.gson.annotations.SerializedName;

public class UserLVL {
    private int result;
    private String content;

    @SerializedName("body")
    private String text;

    public UserLVL(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public String getContent() {
        return content;
    }

    public String getText() {
        return text;
    }
}