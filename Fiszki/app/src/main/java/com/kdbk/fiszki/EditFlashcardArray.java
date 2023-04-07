package com.kdbk.fiszki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditFlashcardArray {
    private static EditFlashcardArray instance = null;
    private Map<Integer, ArrayList<ModelEditFlashcard>> allList = new HashMap<>();

    private EditFlashcardArray() {
        ArrayList<ModelEditFlashcard> subList1 = new ArrayList<>();
        subList1.add(new ModelEditFlashcard(R.drawable.flagpl, "pies",1, 1));
        subList1.add(new ModelEditFlashcard(R.drawable.flagang, "dog:", 2, 1));
        subList1.add(new ModelEditFlashcard(R.drawable.flagpl, "Mój pies lubi kosci", 3, 1));
        subList1.add(new ModelEditFlashcard(R.drawable.flagang, "My dog likes bones", 4, 1));
        allList.put(1, subList1);

        ArrayList<ModelEditFlashcard> subList2 = new ArrayList<>();
        subList2.add(new ModelEditFlashcard(R.drawable.flagpl, "auto", 1, 2));
        subList2.add(new ModelEditFlashcard(R.drawable.flagang, "car", 2, 2));
        subList2.add(new ModelEditFlashcard(R.drawable.flagpl, "Lubie latać bokiem", 3, 2));
        subList2.add(new ModelEditFlashcard(R.drawable.flagang, "I like drifting", 4, 2));
        allList.put(2, subList2);

        ArrayList<ModelEditFlashcard> subList3 = new ArrayList<>();
        subList3.add(new ModelEditFlashcard(R.drawable.flagpl, "mrowka", 1, 3));
        subList3.add(new ModelEditFlashcard(R.drawable.flagang, "ant", 2, 3));
        subList3.add(new ModelEditFlashcard(R.drawable.flagpl, "Bardzo pracowite zwierzęta", 3, 3));
        subList3.add(new ModelEditFlashcard(R.drawable.flagang, "Very hardworking animals", 4, 3));
        allList.put(3, subList3);

        ArrayList<ModelEditFlashcard> subList4 = new ArrayList<>();
        subList4.add(new ModelEditFlashcard(R.drawable.flagpl, "rycerz", 1, 4));
        subList4.add(new ModelEditFlashcard(R.drawable.flagang, "knight", 2, 4));
        subList4.add(new ModelEditFlashcard(R.drawable.flagpl, "Walczy ze smokami", 3, 4));
        subList4.add(new ModelEditFlashcard(R.drawable.flagang, "He fights dragons", 4, 4));
        allList.put(4, subList4);
    }

    public static EditFlashcardArray getInstance() {
        if (instance == null) {
            instance = new EditFlashcardArray();
        }
        return instance;
    }

    public void add(ModelEditFlashcard model) {
        int index = model.getNrWord();
        if (allList.containsKey(index)) {
            ArrayList<ModelEditFlashcard> list = allList.get(index);
            list.add(model);
        } else {
            ArrayList<ModelEditFlashcard> newList = new ArrayList<>();
            newList.add(model);
            allList.put(index, newList);
        }
    }

    public void remove(ModelEditFlashcard model) {
        int index = model.getNrWord();
        if (allList.containsKey(index)) {
            ArrayList<ModelEditFlashcard> list = allList.get(index);
            list.remove(model);
        }
    }

    public void remove(int index) {
        if (allList.containsKey(index)) {
            allList.remove(index);
        }
    }

    public ArrayList<ModelEditFlashcard> getList(int index) {
        if (allList.containsKey(index)) {
            return allList.get(index);
        } else {
            return new ArrayList<ModelEditFlashcard>();
        }
    }

    public int getWords(){
        return allList.size();
    }

}
