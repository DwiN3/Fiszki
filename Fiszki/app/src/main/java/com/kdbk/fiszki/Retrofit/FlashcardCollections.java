package com.kdbk.fiszki.Retrofit;

import java.util.ArrayList;

public class FlashcardCollections {
    private ArrayList<FlashcardsID> flashcards = new ArrayList<FlashcardsID>();

    private String collectionName;
    private String _id;
    public FlashcardCollections(){};
    public FlashcardCollections(String collectionName, String _id){
        this.collectionName = collectionName;
        this._id = _id;
    }

    public String getCollectionName() {
        return collectionName;
    }
    public String getId(){return _id;}

    public ArrayList<FlashcardsID> getList() {
        return flashcards;
    }

    public void setList(ArrayList<FlashcardsID> list) {
        this.flashcards = list;
    }
}
