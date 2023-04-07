package com.kdbk.fiszki;

import java.util.ArrayList;

public class EditFlashcardArray {

    private static EditFlashcardArray instance = null;
    private ArrayList<ModelEditFlashcard> list = new ArrayList<>();

    // Make the constructor public and add a default value for nrWord
    public EditFlashcardArray(int nrWord) {
        int word1=1, word2=2, word3=3, word4=4;

        if(nrWord == 1){
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "pies",1, word1));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "dog:", 2, word1));
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "Mój pies lubi kości", 3, word1));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "My dog likes bones", 4,word1));
        }

        if(nrWord == 2) {
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "auto", 1, word2));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "car", 2, word2));
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "Lubie latać bokiem", 3, word2));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "I like drifting", 4, word2));
        }
        if(nrWord == 3) {
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "mrowka", 1, word3));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "ant", 2, word3));
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "Bardzo pracowite zwierzęta", 3, word3));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "Very hardworking animals", 4, word3));
        }
        if(nrWord == 4) {
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "rycerz", 1, word4));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "knight", 2, word3));
            list.add(new ModelEditFlashcard(R.drawable.flagpl, "Walczy ze smokami", 3, word4));
            list.add(new ModelEditFlashcard(R.drawable.flagang, "He fights dragons", 4, word4));
        }
    }

    public static EditFlashcardArray getInstance(int nrWord) {
        if (instance == null || instance.getList().isEmpty() || instance.getList().get(0).getNrWord() != nrWord) {
            instance = new EditFlashcardArray(nrWord);
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
