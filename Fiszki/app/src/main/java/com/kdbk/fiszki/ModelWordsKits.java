package com.kdbk.fiszki;

public class ModelWordsKits {

    private String textID;
    private int ID;

    ModelWordsKits(String textID, int ID){
        this.textID = textID;
        this.ID = ID;
    }

    public String getTextID() {
        return textID;
    }

    public int getID() {
        return ID;
    }
}
