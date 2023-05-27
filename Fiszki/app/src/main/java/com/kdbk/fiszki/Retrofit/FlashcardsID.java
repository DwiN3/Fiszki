package com.kdbk.fiszki.Retrofit;

import com.google.gson.annotations.SerializedName;

public class FlashcardsID {
    private String word, translatedWord, example, translatedExample, content;

    @SerializedName("body")
    private String text;

    public FlashcardsID() {

    }

    public FlashcardsID(String word, String translatedWord, String example, String translatedExample) {
        this.word = word;
        this.translatedWord = translatedWord;
        this.example = example;
        this.translatedExample = translatedExample;
    }

    public String getWord() {
        return word;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public String getExample() {
        return example;
    }

    public String getTranslatedExample() {
        return translatedExample;
    }

    public String getContent() {
        return content;
    }

    public String getText() {
        return text;
    }
}
