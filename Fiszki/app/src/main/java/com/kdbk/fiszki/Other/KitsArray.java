package com.kdbk.fiszki.Other;

import com.kdbk.fiszki.Model.ModelKits;

import java.util.ArrayList;

public class KitsArray {
    private static KitsArray instance;
    private ArrayList<ModelKits> list;

    private KitsArray() {
        list = new ArrayList<ModelKits>();
        WordsArray wordsArray = new WordsArray();
        list.add(new ModelKits("Zestaw 1", "ILOSC FISZEK", String.valueOf(wordsArray.getLenOfArray()), 1, 5));
        list.add(new ModelKits("Zestaw 2", "ILOSC FISZEK", "18", 2, 31));
        list.add(new ModelKits("Zestaw 3", "ILOSC FISZEK", "25", 3, 21));
        list.add(new ModelKits("Zestaw 4", "ILOSC FISZEK", "30", 4, 3));
        list.add(new ModelKits("Zestaw 5", "ILOSC FISZEK", "11", 5, 15));
    }

    public static KitsArray getInstance() {
        if (instance == null) {
            instance = new KitsArray();
        }
        return instance;
    }

    public ArrayList<ModelKits> getList() {
        return list;
    }
}
