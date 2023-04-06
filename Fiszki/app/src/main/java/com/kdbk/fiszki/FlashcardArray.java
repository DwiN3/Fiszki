package com.kdbk.fiszki;

import java.util.ArrayList;

public class FlashcardArray {
    private static FlashcardArray instance = null;
    private ArrayList<ModelFlashcard> list = new ArrayList<>();

    private FlashcardArray() {
        list.add(new ModelFlashcard(R.drawable.arrow, "NUMER ZESTAWU", "1", 1));
        list.add(new ModelFlashcard(R.drawable.flagpl, "WPROWADŹ SŁOWO:", "pies", 2));
        list.add(new ModelFlashcard(R.drawable.flagang, "DODAJ TŁUMACZENIE:", "dog", 3));
        list.add(new ModelFlashcard(R.drawable.flagpl, "PRZYKŁADOWE ZDANIE", "Mój pies lubi kości", 4));
        list.add(new ModelFlashcard(R.drawable.flagang, "PRZYKŁADOWE ZDANIE", "My dog likes bones", 5));
    }

    public static FlashcardArray getInstance() {
        if (instance == null) {
            instance = new FlashcardArray();
        }
        return instance;
    }

    public void add(ModelFlashcard model) {
        list.add(model);
    }

    public void remove(ModelFlashcard model) {
        list.remove(model);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public ArrayList<ModelFlashcard> getList() {
        return list;
    }
}
