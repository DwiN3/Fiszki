package com.kdbk.fiszki.RecyclerView.Model;

import com.kdbk.fiszki.Retrofit.Flashcards;
import com.kdbk.fiszki.Retrofit.FlashcardsID;

import java.util.ArrayList;
import java.util.List;

public class ModelKits {

    private int textNumberOfCards;
    private String textNumberKit;
    private String textTEXTflashcards;
    private int ID;
    private int gamesPlayed;
    private String _id;
    private ArrayList<FlashcardsID> list = new ArrayList<FlashcardsID>();

    public ModelKits(String textNumberKit, String TextTEXTflashcards, int textNumberOfCards, int ID, int gamesPlayed, String _id){
        this.textNumberOfCards = textNumberOfCards;
        this.textNumberKit = textNumberKit;
        this.textTEXTflashcards = TextTEXTflashcards;
        this.ID = ID;
        this.gamesPlayed = gamesPlayed;
        this._id = _id;
    }

    public int getTextNumberOfCards() {
        return textNumberOfCards;
    }

    public String getTextNumberKit() {
        return textNumberKit;
    }

    public String getTextTEXTflashcards() {
        return textTEXTflashcards;
    }

    public int getID() {
        return ID;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public String get_id() {
        return _id;
    }

    public ArrayList<FlashcardsID> getList() {
        return list;
    }

    public void setList(ArrayList<FlashcardsID> list) {
        this.list = list;
    }

}
