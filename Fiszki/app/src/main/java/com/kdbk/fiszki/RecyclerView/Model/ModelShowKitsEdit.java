package com.kdbk.fiszki.RecyclerView.Model;

public class ModelShowKitsEdit {

    private int ID;

    private String word, translateWord,sentens,sentensTranslate;

    public ModelShowKitsEdit(String word, String translateWord, String sentens, String sentensTranslate, int ID){
        this.word = word;
        this.translateWord = translateWord;
        this.sentens = sentens;
        this.sentensTranslate = sentensTranslate;
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
