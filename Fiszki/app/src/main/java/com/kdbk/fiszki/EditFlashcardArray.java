package com.kdbk.fiszki;

import java.util.ArrayList;
import java.util.Collection;

public class EditFlashcardArray {

    private static EditFlashcardArray instance = null;
    private ArrayList<ArrayList<ModelEditFlashcard>> list = new ArrayList<>();

    // Make the constructor public and add a default value for nrWord
    public EditFlashcardArray(int nrWord) {
        ArrayList<ModelEditFlashcard> subList1 = new ArrayList<>();
        subList1.add(new ModelEditFlashcard(R.drawable.flagpl, "pies",1, 1));
        subList1.add(new ModelEditFlashcard(R.drawable.flagang, "dog:", 2, 1));
        subList1.add(new ModelEditFlashcard(R.drawable.flagpl, "Mój pies lubi kości", 3, 1));
        subList1.add(new ModelEditFlashcard(R.drawable.flagang, "My dog likes bones", 4, 1));

        ArrayList<ModelEditFlashcard> subList2 = new ArrayList<>();
        subList2.add(new ModelEditFlashcard(R.drawable.flagpl, "auto", 1, 2));
        subList2.add(new ModelEditFlashcard(R.drawable.flagang, "car", 2, 2));
        subList2.add(new ModelEditFlashcard(R.drawable.flagpl, "Lubie latać bokiem", 3, 2));
        subList2.add(new ModelEditFlashcard(R.drawable.flagang, "I like drifting", 4, 2));

        ArrayList<ModelEditFlashcard> subList3 = new ArrayList<>();
        subList3.add(new ModelEditFlashcard(R.drawable.flagpl, "mrowka", 1, 3));
        subList3.add(new ModelEditFlashcard(R.drawable.flagang, "ant", 2, 3));
        subList3.add(new ModelEditFlashcard(R.drawable.flagpl, "Bardzo pracowite zwierzęta", 3, 3));
        subList3.add(new ModelEditFlashcard(R.drawable.flagang, "Very hardworking animals", 4, 3));

        ArrayList<ModelEditFlashcard> subList4 = new ArrayList<>();
        subList4.add(new ModelEditFlashcard(R.drawable.flagpl, "rycerz", 1, 4));
        subList4.add(new ModelEditFlashcard(R.drawable.flagang, "knight", 2, 4));
        subList4.add(new ModelEditFlashcard(R.drawable.flagpl, "Walczy ze smokami", 3, 4));
        subList4.add(new ModelEditFlashcard(R.drawable.flagang, "He fights dragons", 4, 4));

        list.add(subList1);
        list.add(subList2);
        list.add(subList3);
        list.add(subList4);
    }

    public static EditFlashcardArray getInstance(int nrWord) {
        if (instance == null) {
            instance = new EditFlashcardArray(nrWord);
        }
        return instance;
    }

    public void add(ModelEditFlashcard model, int subListIndex) {
        list.get(subListIndex).add(model);
    }

    public void remove(ModelEditFlashcard model, int subListIndex) {
        list.get(subListIndex).remove(model);
    }

    public void remove(int subListIndex, int index) {
        list.get(subListIndex).remove(index);
    }

    public Collection<? extends ModelEditFlashcard> getList(int nr) {
        return list.get(nr-1);
    }
}
