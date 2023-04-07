package com.kdbk.fiszki;

public class ModelEditFlashcard {

    private String editWord;
    private int imageResource;
    private int cardId;
    private int nrWord;

    public ModelEditFlashcard(int imageResource, String editWord, int cardId, int nrWord) {
        this.editWord = editWord;
        this.imageResource = imageResource;
        this.cardId = cardId;
        this.nrWord = nrWord;
    }

    public String getEditWord() {
        return editWord;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getCardId() {
        return cardId;
    }
    public int getNrWord() {
        return nrWord;
    }
}
