package com.kdbk.fiszki;

public class ModelFlashcard {
    private String nameSettings;
    private String editFlashcard;
    private int imageResource;
    private int cardId;

    public ModelFlashcard(int imageResource, String nameSettings, String editFlashcard, int cardId) {
        this.nameSettings = nameSettings;
        this.editFlashcard = editFlashcard;
        this.imageResource = imageResource;
        this.cardId = cardId;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getNameSettings() {
        return nameSettings;
    }

    public String getEditFlashcard() {
        return editFlashcard;
    }

    public int getcardId() {
        return cardId;
    }
}
