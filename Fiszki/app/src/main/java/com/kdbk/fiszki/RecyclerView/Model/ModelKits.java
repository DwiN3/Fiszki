package com.kdbk.fiszki.RecyclerView.Model;
import com.kdbk.fiszki.Retrofit.FlashcardID;
import java.util.ArrayList;

public class ModelKits {
    private int textNumberOfCards, ID, gamesPlayed;
    private String textNumberKit, textTEXTflashcards, _id;
    private ArrayList<FlashcardID> list = new ArrayList<FlashcardID>();

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

    public ArrayList<FlashcardID> getList() {
        return list;
    }

    public void setList(ArrayList<FlashcardID> list) {
        this.list = list;
    }

}
