package com.kdbk.fiszki.Other;

import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SetGameClass {
    private String[] NameWord;
    private String[] correctANS;
    private String[] sentense;
    private String[] sentenseTra;
    private String[] ans1;
    private String[] ans2;
    private String[] ans3;
    private String[] ans4;
    private int words;
    private ArrayList<ModelShowKitsEdit> wordsList;
    private int img_One = R.drawable.flagpl;
    private int imgPL = R.drawable.flagpl;
    private int imgENG = R.drawable.flagang;
    private int borrder=30;

    public SetGameClass(String data,String mode, ArrayList<ModelShowKitsEdit> wordsListAll) {
        if(mode.equals("quiz")){
            Random randomWords = new Random();
            Set<Integer> selectedIndices = new HashSet<>();
            ArrayList<ModelShowKitsEdit> selectedWords = new ArrayList<>();
            if(wordsListAll.size() <= borrder) borrder = wordsListAll.size();

            while (selectedIndices.size() < borrder) {
                int randomIndex = randomWords.nextInt(wordsListAll.size());

                if (!selectedIndices.contains(randomIndex)) {
                    selectedIndices.add(randomIndex);
                    selectedWords.add(wordsListAll.get(randomIndex));
                }
            }
            wordsList = selectedWords;
        }
        else wordsList = wordsListAll;


        this.NameWord = new String[wordsList.size()];
        this.correctANS = new String[wordsList.size()];
        this.sentense = new String[wordsList.size()];
        this.sentenseTra = new String[wordsList.size()];
        this.ans1 = new String[wordsList.size()];
        this.ans2 = new String[wordsList.size()];
        this.ans3 = new String[wordsList.size()];
        this.ans4 = new String[wordsList.size()];

        if (data.equals("kit")) {
            Random random = new Random();
            int wordsListSize = wordsList.size();
            for (int i = 0; i < wordsList.size(); i++) {
                NameWord[i] = wordsList.get(i).getWord();
                correctANS[i] = wordsList.get(i).getTranslateWord();
                sentense[i] = wordsList.get(i).getSentens();
                sentenseTra[i] = wordsList.get(i).getSentensTranslate();

                Set<String> uniqueWords = new HashSet<>();
                uniqueWords.add(correctANS[i]);

                while (uniqueWords.size() < 4) {
                    int randomIndex = random.nextInt(wordsList.size());
                    String randomWord = wordsList.get(randomIndex).getTranslateWord();
                    uniqueWords.add(randomWord);
                }

                String[] uniqueWordsArray = uniqueWords.toArray(new String[0]);
                ans1[i] = uniqueWordsArray[0];
                ans2[i] = uniqueWordsArray[1];
                ans3[i] = uniqueWordsArray[2];
                ans4[i] = uniqueWordsArray[3];
            }
        }
        words = wordsList.size();
    }




    public String getNameWord(int i) {
        return NameWord[i];
    }

    public String getAns1(int i) {
        return ans1[i];
    }

    public String getAns2(int i) {
        return ans2[i];
    }

    public String getAns3(int i) {
        return ans3[i];
    }

    public String getAns4(int i) {
        return ans4[i];
    }

    public String getCorrectANS(int i) {
        return correctANS[i];
    }

    public String getSentense(int i) {
        return sentense[i];
    }

    public String getSentenseTra(int i) {
        return sentenseTra[i];
    }

    public int getWords() {
        return words;
    }

    public int getImgPL() {
        return imgPL;
    }

    public int getImg_One() {
        return img_One;
    }

    public int getImgENG() {
        return imgENG;
    }
    public int getBorrder() {
        return borrder;
    }
}