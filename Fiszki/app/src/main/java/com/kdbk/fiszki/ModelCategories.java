package com.kdbk.fiszki;

public class ModelCategories {

    private int ImageResource;
    private String nameCategory;
    private String ID;

    ModelCategories(int ImageResource, String nameCategory){
        this.ImageResource = ImageResource;
        this.nameCategory = nameCategory;
        this.ID = ID;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public String getID() {
        return ID;
    }
}
