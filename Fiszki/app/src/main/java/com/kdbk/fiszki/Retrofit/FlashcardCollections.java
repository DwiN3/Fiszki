package com.kdbk.fiszki.Retrofit;

import java.util.ArrayList;

public class FlashcardCollections {
        private String collectionName;
        private String _id;
        //private ArrayList<FlashcardsID> flashcards;

        public FlashcardCollections() {
        }

        public FlashcardCollections(String collectionName, String _id) {
            this.collectionName = collectionName;
            this._id = _id;
        }

        public String getCollectionName() {
            return collectionName;
        }

        public String getId() {
            return _id;
        }

        /*public ArrayList<FlashcardsID> getFlashcards() {
            return flashcards;
        }

        public void setFlashcards(ArrayList<FlashcardsID> flashcards) {
            this.flashcards = flashcards;
        }*/
}
