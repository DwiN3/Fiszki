package com.kdbk.fiszki.Other;

import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    private int img_One = R.drawable.flagpl;
    private int imgPL = R.drawable.flagpl;
    private int imgENG = R.drawable.flagang;

    public SetGameClass(String data, ArrayList<ModelShowKitsEdit> wordsList) {
        this.NameWord = new String[wordsList.size()];
        this.correctANS = new String[wordsList.size()];
        this.sentense = new String[wordsList.size()];
        this.sentenseTra = new String[wordsList.size()];
        this.ans1 = new String[wordsList.size()];
        this.ans2 = new String[wordsList.size()];
        this.ans3 = new String[wordsList.size()];
        this.ans4 = new String[wordsList.size()];

        if (data.equals("kit")) {
            for (int i = 0; i < wordsList.size(); i++) {
                NameWord[i] = wordsList.get(i).getWord();
                correctANS[i] = wordsList.get(i).getTranslateWord();
                sentense[i] = wordsList.get(i).getSentens();
                sentenseTra[i] = wordsList.get(i).getSentensTranslate();
                setRandomAnswers(wordsList, i);
            }
        }

        else {
            NameWord = new String[]{"pies", "niedzwiedz", "krowa", "ślimak"};
            correctANS = new String[]{"dog", "bear", "cow", "snail"};
            sentense = new String[]{"To najlepszy przyjaciel człowieka", "Są dużymi zwierzętami", "Dają mleko", "On porusza się bardzo wolno"};
            sentenseTra = new String[]{"It's humans best friend", "They are large animals", "Give milk", "He moves very slowly"};
            ans1 = new String[]{"dog", "bird", "goat", "beaver"};
            ans2 = new String[]{"duck", "bee", "cow", "chicken"};
            ans3 = new String[]{"cat", "bear", "lion", "pig"};
            ans4 = new String[]{"worm", "bat", "sheep", "snail"};
        }

        words = wordsList.size();
    }

    private void setRandomAnswers(ArrayList<ModelShowKitsEdit> wordsList, int currentIndex) {
        ArrayList<String> wordList = new ArrayList<>();
        for (ModelShowKitsEdit word : wordsList) {
            wordList.add(word.getTranslateWord());
        }

        int correctAnswerIndex = wordList.indexOf(correctANS[currentIndex]);
        wordList.remove(correctAnswerIndex);
        Collections.shuffle(wordList);

        ans1[currentIndex] = wordList.get(0);
        ans2[currentIndex] = wordList.get(1);
        ans3[currentIndex] = wordList.get(2);
        ans4[currentIndex] = correctANS[currentIndex];
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
}
