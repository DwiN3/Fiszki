package com.kdbk.fiszki.Other;

public class TESTGameClass {

    private String[] NameWord = {"Pies", "Niedzwiedz", "Krowa", "Ślimak"};
    private String[] ans1 = {"dog", "bird", "goat", "beaver"};
    private String[] ans2= {"duck", "bee", "cow", "chicken"};
    private String[] ans3= {"cat", "bear", "lion", "pig"};
    private String[] ans4= {"worm", "bat", "sheep", "snail"};
    private String[] correctANS = {"dog", "bear", "cow", "snail"};

    private int words = NameWord.length;

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

    public int getWords() {
        return words;
    }
}
