package com.kdbk.fiszki.Other;

import com.kdbk.fiszki.LocalDate.WordsArray;
import com.kdbk.fiszki.R;
import com.kdbk.fiszki.RecyclerView.Model.ModelShowKitsEdit;

import java.util.ArrayList;

public class SetGameClass {

    private WordsArray kitsWords  = WordsArray.getInstance();
    private WordsArray wordsArray = new WordsArray();

    private String[] NameWord;
    private String[] correctANS;
    private String[] sentense;
    private String[] sentenseTra;
    private int[] img;
    private int img_One = R.drawable.flagpl;
    private int imgPL = R.drawable.flagpl;
    private int imgENG = R.drawable.flagang;
    private int words;

    public SetGameClass(String data, ArrayList<ModelShowKitsEdit> wordsList) {
        this.NameWord = new String[wordsList.size()];
        this.correctANS = new String[wordsList.size()];
        this.sentense = new String[wordsList.size()];
        this.sentenseTra = new String[wordsList.size()];

        if (data.equals("kit")) {
            for (int i = 0; i < wordsList.size(); i++) {
                NameWord[i] = wordsList.get(i).getWord();
                correctANS[i] = wordsList.get(i).getTranslateWord();
                sentense[i] = wordsList.get(i).getSentens();
                sentenseTra[i] = wordsList.get(i).getSentensTranslate();
            }
            imgPL =  R.drawable.flagpl;
            /*ans1 = new String[]{"tram", "motor", "bat", "shild"};
            ans2 = new String[]{"train", "car", "ant", "sword"};
            ans3 = new String[]{"rolls", "bike", "seal", "dragon"};
            ans4 = new String[]{"bus", "plane", "chair", "knight"};
            img = new int[]{R.drawable.word_train, R.drawable.word_car, R.drawable.word_ant, R.drawable.word_knight};*/
        } else {
            NameWord = new String[]{"pies", "niedzwiedz", "krowa", "ślimak"};
            correctANS = new String[]{"dog", "bear", "cow", "snail"};
            sentense = new String[]{"To najlepszy przyjaciel człowieka", "Są dużymi zwierzętami", "Dają mleko", "On porusza się bardzo wolno"};
            sentenseTra = new String[]{"It's humans best friend", "They are large animals", "Give milk", "He moves very slowly"};
            img = new int[]{R.drawable.word_dog, R.drawable.word_bear, R.drawable.word_cow, R.drawable.word_snail};
            ans1 = new String[]{"dog", "bird", "goat", "beaver"};
            ans2 = new String[]{"duck", "bee", "cow", "chicken"};
            ans3 = new String[]{"cat", "bear", "lion", "pig"};
            ans4 = new String[]{"worm", "bat", "sheep", "snail"};
        }
        words = NameWord.length;
    }


    private String[] ans1;
    private String[] ans2;
    private String[] ans3;
    private String[] ans4;

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
    public int getImg(int i) {
        return img[i];
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
