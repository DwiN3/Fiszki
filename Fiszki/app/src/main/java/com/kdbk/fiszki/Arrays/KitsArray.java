package com.kdbk.fiszki.Arrays;

import com.kdbk.fiszki.RecyclerView.Model.ModelKits;

import java.util.ArrayList;

public class KitsArray {
    private static KitsArray instance;
    private ArrayList<ModelKits> list;

    private KitsArray() {
        list = new ArrayList<ModelKits>();
        list.add(new ModelKits("Zestaw 1", "ILOSC FISZEK", "30", 0, 5, ""));
        list.add(new ModelKits("Zestaw 2", "ILOSC FISZEK", "18", 1, 31, ""));
        list.add(new ModelKits("Zestaw 3", "ILOSC FISZEK", "25", 2, 21, ""));
        list.add(new ModelKits("Zestaw 4", "ILOSC FISZEK", "30", 3, 3, ""));
        list.add(new ModelKits("Zestaw 5", "ILOSC FISZEK", "11", 4, 15, ""));
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
