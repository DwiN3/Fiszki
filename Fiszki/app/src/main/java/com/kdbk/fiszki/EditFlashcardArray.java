package com.kdbk.fiszki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditFlashcardArray {
    private static EditFlashcardArray instance = null;
    private Map<Integer, ArrayList<ModelEditFlashcard>> allList = new HashMap<>();

    private EditFlashcardArray() {
        ArrayList<ModelEditFlashcard> list1 = new ArrayList<>();
        int word1 = 1, word2 = 2;
        list1.add(new ModelEditFlashcard(R.drawable.flagpl, "pies",1, word1));
        list1.add(new ModelEditFlashcard(R.drawable.flagang, "dog:", 2, word1));
        list1.add(new ModelEditFlashcard(R.drawable.flagpl, "Mój pies lubi kosci", 3, word1));
        list1.add(new ModelEditFlashcard(R.drawable.flagang, "My dog likes bones", 4, word1));
        allList.put(word1, list1);

        ArrayList<ModelEditFlashcard> list2 = new ArrayList<>();
        list2.add(new ModelEditFlashcard(R.drawable.flagpl, "lol",1, word2));
        list2.add(new ModelEditFlashcard(R.drawable.flagang, "fsdf:", 2, word2));
        list2.add(new ModelEditFlashcard(R.drawable.flagpl, "gs", 3, word2));
        list2.add(new ModelEditFlashcard(R.drawable.flagang, "gdsgsd", 4, word2));
        allList.put(word2, list2);
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
}
