package com.kdbk.fiszki.Other;

import com.kdbk.fiszki.Model.ModelEditFlashcard;
import com.kdbk.fiszki.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

    public class EditFlashcardArray {
        private static com.kdbk.fiszki.Other.EditFlashcardArray instance = null;
        private Map<Integer, ArrayList<ModelEditFlashcard>> allList = new HashMap<>();
        WordsArray wordsArray = new WordsArray();

        private EditFlashcardArray() {

            for (int i = 0; i < wordsArray.getLenOfArray(); i++) {
                ArrayList<ModelEditFlashcard> subList = new ArrayList<>();
                String[] minilist = wordsArray.getWordsString(i);
                subList.add(new ModelEditFlashcard(R.drawable.flagpl, minilist[0], 1, i));
                subList.add(new ModelEditFlashcard(R.drawable.flagang, minilist[1], 2, i));
                subList.add(new ModelEditFlashcard(R.drawable.flagpl, minilist[2], 3, i));
                subList.add(new ModelEditFlashcard(R.drawable.flagang, minilist[3], 4, i));
                allList.put(i, subList);
            }
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
        if (allList.containsKey(index)){
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

    public Map<Integer, ArrayList<ModelEditFlashcard>> getAllList(){
        return allList;
    }

    public ArrayList<ArrayList<ModelEditFlashcard>> getAllLists() {
        return new ArrayList<>(allList.values());
    }

}
