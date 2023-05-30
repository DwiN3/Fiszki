package com.kdbk.fiszki.RecyclerView.Model;

public class ModelShowKitsEdit {
    int ID;
    private String word, translateWord,sentens,sentensTranslate, _id;

    public ModelShowKitsEdit(String word, String translateWord, String sentens, String sentensTranslate, int ID, String _id){
        this.word = word;
        this.translateWord = translateWord;
        this.sentens = sentens;
        this.sentensTranslate = sentensTranslate;
        this._id = _id;
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

    public String get_id() {
        return _id;
    }
}
