package com.challenge.omurkumru.babbel.model;

public class GameWord {

    private String enWord;
    private String espWord;
    private boolean isTrue;

    public GameWord(String enWord, String espWord, boolean isTrue) {
        this.enWord = enWord;
        this.espWord = espWord;
        this.isTrue = isTrue;
    }

    public String getEnWord() {
        return enWord;
    }

    public String getEspWord() {
        return espWord;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
