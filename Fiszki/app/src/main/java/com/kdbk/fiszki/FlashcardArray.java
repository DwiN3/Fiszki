package com.kdbk.fiszki;

import java.util.ArrayList;

public class FlashcardArray {
    private static FlashcardArray instance = null;
    private ArrayList<ModelAddFlashcard> list = new ArrayList<>();

    private FlashcardArray() {
        list.add(new ModelAddFlashcard(R.drawable.arrow, "NUMER ZESTAWU", "1", 1));
        list.add(new ModelAddFlashcard(R.drawable.flagpl, "WPROWADŹ SŁOWO:", "", 2));
        list.add(new ModelAddFlashcard(R.drawable.flagang, "DODAJ TŁUMACZENIE:", "", 3));
        list.add(new ModelAddFlashcard(R.drawable.flagpl, "PRZYKŁADOWE ZDANIE", "", 4));
        list.add(new ModelAddFlashcard(R.drawable.flagang, "PRZYKŁADOWE ZDANIE", "", 5));
    }

    public static FlashcardArray getInstance() {
        if (instance == null) {
            instance = new FlashcardArray();
        }
        return instance;
    }

    public void add(ModelAddFlashcard model) {
        list.add(model);
    }

    public void remove(ModelAddFlashcard model) {
        list.remove(model);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public ArrayList<ModelAddFlashcard> getList() {
        return list;
    }
}
