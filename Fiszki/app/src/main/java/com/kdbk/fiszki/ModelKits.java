package com.kdbk.fiszki;

public class ModelKits {

    private String textNumberOfCards;
    private String textNumberKit;
    private String textTEXTflashcards;
    private String ID;

    ModelKits(String textNumberKit, String TextTEXTflashcards, String textNumberOfCards){
        this.textNumberOfCards = textNumberOfCards;
        this.textNumberKit = textNumberKit;
        this.textTEXTflashcards = TextTEXTflashcards;
        this.ID = ID;
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

    public String getID() {
        return ID;
    }
}
