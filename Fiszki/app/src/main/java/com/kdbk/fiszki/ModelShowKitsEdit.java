package com.kdbk.fiszki;

public class ModelShowKitsEdit {

    private int ID;

    private String word, translateWord,sentens,sentensTranslate;

    ModelShowKitsEdit(String word, String translateWord, String sentens, String sentensTranslate, int ID){

        this.ID = ID;
    }


    public int getID() {
        return ID;
    }
    public String getWord() {
        return word;
    }

    public String getTranslateWord() {
        return translateWord;
    }

    public String getSentens() {
        return sentens;
    }

    public String getSentensTranslate() {
        return sentensTranslate;
    }
}
