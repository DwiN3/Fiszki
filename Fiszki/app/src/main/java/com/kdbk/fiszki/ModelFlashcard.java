package com.kdbk.fiszki;

public class ModelFlashcard {

    private int ImageResource;
    private String nameSettings;
    private String editFlashcard;
    private int ID;

    ModelFlashcard(int ImageResource, String nameSettings, String editFlashcard, int ID){
        this.ImageResource = ImageResource;
        this.nameSettings = nameSettings;
        this.editFlashcard = editFlashcard;
        this.ID = ID;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public String getNameSettings() {
        return nameSettings;
    }

    public String getEditFlashcard() {
        return editFlashcard;
    }

    public int getID() {
        return ID;
    }

}
