package com.kdbk.fiszki.RecyclerView.Model;

public class ModelCategories {

    private int ImageResource;
    private String nameCategory;
    private int ID;

    public ModelCategories(int ImageResource, String nameCategory, int ID){
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

    public int getID() {
        return ID;
    }
}
