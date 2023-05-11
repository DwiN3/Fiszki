package com.kdbk.fiszki.Other;

import com.kdbk.fiszki.R;

public class TESTGameClass {

    private String[] NameWord = {"pies", "niedzwiedz", "krowa", "ślimak"};
    private String[] correctANS = {"dog", "bear", "cow", "snail"};
    private String[] sentense = {"To najlepszy przyjaciel człowieka", "Są dużymi zwierzętami", "Dają mleko", "On porusza się bardzo wolno"};
    private String[] sentenseTra = {"It's humans best friend", "They are large animals", "Give milk", "He moves very slowly"};
    private int[] img = {R.drawable.word_dog, R.drawable.word_bear, R.drawable.word_cow, R.drawable.word_snail};
    private int words = NameWord.length;


    private String[] ans1 = {"dog", "bird", "goat", "beaver"};
    private String[] ans2= {"duck", "bee", "cow", "chicken"};
    private String[] ans3= {"cat", "bear", "lion", "pig"};
    private String[] ans4= {"worm", "bat", "sheep", "snail"};

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
}
