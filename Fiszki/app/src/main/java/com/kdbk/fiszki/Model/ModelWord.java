package com.kdbk.fiszki.Model;

public class ModelWord {
    private String word, translateWord, sampleSentence, translateSampleSentence;
    private int id;

    public ModelWord(String word, String translateWord, String sampleSentence, String translateSampleSentence, int id) {
        this.word = word;
        this.translateWord = translateWord;
        this.sampleSentence = sampleSentence;
        this.translateSampleSentence = translateSampleSentence;
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public String getTranslateWord() {
        return translateWord;
    }

    public String getSampleSentence() {
        return sampleSentence;
    }

    public String getTranslateSampleSentence() {
        return translateSampleSentence;
    }

    public int getId() {
        return id;
    }
}
