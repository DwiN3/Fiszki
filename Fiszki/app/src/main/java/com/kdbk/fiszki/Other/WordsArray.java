package com.kdbk.fiszki.Other;
import com.kdbk.fiszki.Model.ModelWord;
import java.util.ArrayList;

public class WordsArray {
    private static WordsArray instance = null;
    ArrayList<ModelWord> list;

    public WordsArray() {
        list = new ArrayList<>();
        list.add(new ModelWord("auto", "car", "Lubie latac bokiem", "I like drifting", 0));
        list.add(new ModelWord("mrowka", "ant", "Bardzo pracowite zwierzęta", "Very hardworking animals", 1));
        list.add(new ModelWord("auto", "car", "Lubie latac bokiem", "I like drifting", 2));
        list.add(new ModelWord("rycerz", "knight", "Walczy ze smokami", "He fights dragons", 3));
    }


    public String[] getWordsString(int nrWord){
        String choseWord[] = {list.get(nrWord).getWord(),list.get(nrWord).getTranslateWord(), list.get(nrWord).getSampleSentence(), list.get(nrWord).getTranslateSampleSentence()};
        return choseWord;
    }

    public int getLenOfArray(){
        return list.size();
    }
}
