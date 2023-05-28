package com.kdbk.fiszki.RecyclerView.Model;

public class ModelKits {

    private String textNumberOfCards;
    private String textNumberKit;
    private String textTEXTflashcards;
    private int ID;
    private int gamesPlayed;
    private String _id;

    public ModelKits(String textNumberKit, String TextTEXTflashcards, String textNumberOfCards, int ID, int gamesPlayed, String _id){
        this.textNumberOfCards = textNumberOfCards;
        this.textNumberKit = textNumberKit;
        this.textTEXTflashcards = TextTEXTflashcards;
        this.ID = ID;
        this.gamesPlayed = gamesPlayed;
        this._id = _id;
    }

    public String getTextNumberOfCards() {
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
}
