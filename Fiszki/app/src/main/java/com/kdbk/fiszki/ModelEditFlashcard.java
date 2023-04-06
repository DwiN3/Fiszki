package com.kdbk.fiszki;

public class ModelEditFlashcard {

    private String editWord;
    private int imageResource;
    private int cardId;

    public ModelEditFlashcard(int imageResource, String editWord, int cardId) {
        this.editWord = editWord;
        this.imageResource = imageResource;
        this.cardId = cardId;
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
}
