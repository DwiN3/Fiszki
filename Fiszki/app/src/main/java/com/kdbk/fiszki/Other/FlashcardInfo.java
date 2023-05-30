package com.kdbk.fiszki.Other;

public class FlashcardInfo {
    private static FlashcardInfo instance = null;
    private static String nameCollection ="", id_word="";


    public static FlashcardInfo getInstance() {
        if (instance == null) {
            instance = new FlashcardInfo();
        }
        return instance;
    }

    public String getNameCollection() {
        return nameCollection;
    }

    public void setNameCollection(String nameCollection) { FlashcardInfo.nameCollection = nameCollection; }

    public void setId_word(String id_word) {
        FlashcardInfo.id_word = id_word;
    }

    public String getId_word() {return id_word;}
}
