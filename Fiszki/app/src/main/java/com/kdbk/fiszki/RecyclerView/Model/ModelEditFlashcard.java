package com.kdbk.fiszki.RecyclerView.Model;

public class ModelEditFlashcard {

    private String editWord;
    private int imageResource;
    private int cardId;
    private int nrWord;

    public ModelEditFlashcard(int imageResource, String editWord, int cardId, int nrWord) {
        this.editWord = editWord;
        this.imageResource = imageResource;
        this.cardId = cardId;
    }

    public void setEditWord(String editWord) {
        this.editWord = editWord;
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
