package com.kdbk.fiszki.Retrofit;

public class FlashcardCollections {

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

}
