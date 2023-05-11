package com.kdbk.fiszki.Arrays;
import com.kdbk.fiszki.Other.Token;
import com.kdbk.fiszki.Other.Words;
import java.util.ArrayList;

public class WordsArray {
    private static WordsArray instance = null;

    public static WordsArray getInstance() {
        if (instance == null) {
            instance = new WordsArray();
        }
        return instance;
    }
    ArrayList<Words> list;

    public WordsArray() {
        list = new ArrayList<>();
        list.add(new Words("pociąg", "train", "Pociąg to świetny transport", "The train is a great means of transport", 0));
        list.add(new Words("auto", "car", "Lubie latac bokiem", "I like drifting", 1));
        list.add(new Words("mrowka", "ant", "Bardzo pracowite zwierzęta", "Very hardworking animals", 2));
        list.add(new Words("rycerz", "knight", "Walczy ze smokami", "He fights dragons", 3));
    }


    public String[] getWordsString(int nrWord){
        String choseWord[] = {list.get(nrWord).getWord(),list.get(nrWord).getTranslateWord(), list.get(nrWord).getSampleSentence(), list.get(nrWord).getTranslateSampleSentence()};
        return choseWord;
    }

    public int getLenOfArray(){
        return list.size();
    }

    public void removeWord(int index){
        list.remove(index);
    }

    public void addWord(String[] listWord){ // NIE DZIAŁA POPRAWNIE
        //int size = list.size();
        //list.add(new ModelWord(listWord[1], listWord[2], listWord[3], listWord[4], size));
    }

}
