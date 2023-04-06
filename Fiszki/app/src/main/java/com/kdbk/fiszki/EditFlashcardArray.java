package com.kdbk.fiszki;

import java.util.ArrayList;

public class EditFlashcardArray {

    private static EditFlashcardArray instance = null;
    private ArrayList<ModelEditFlashcard> list = new ArrayList<>();

    private EditFlashcardArray() {
        list.add(new ModelEditFlashcard(R.drawable.flagpl, "pies",1));
        list.add(new ModelEditFlashcard(R.drawable.flagang, "dog:", 2));
        list.add(new ModelEditFlashcard(R.drawable.flagpl, "Mój pies lubi kosci", 3));
        list.add(new ModelEditFlashcard(R.drawable.flagang, "My dog likes bones", 4));
    }

    public static EditFlashcardArray getInstance() {
        if (instance == null) {
            instance = new EditFlashcardArray();
        }
        return instance;
    }

    public void add(ModelEditFlashcard model) {
        list.add(model);
    }

    public void remove(ModelEditFlashcard model) {
        list.remove(model);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public ArrayList<ModelEditFlashcard> getList() {
        return list;
    }
}
