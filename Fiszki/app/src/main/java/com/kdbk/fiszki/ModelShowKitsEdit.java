package com.kdbk.fiszki;

public class ModelShowKitsEdit {

    private String textID;
    private int ID;

    ModelShowKitsEdit(String textID, int ID){
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
