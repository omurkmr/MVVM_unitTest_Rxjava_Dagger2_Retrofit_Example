package com.challenge.omurkumru.babbel.ui.game;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.challenge.omurkumru.babbel.model.GameWord;
import com.challenge.omurkumru.babbel.model.Word;

import java.util.List;
import java.util.Random;

public class GameViewModel extends ViewModel {

    private GameRepository repository;

    private MutableLiveData<Integer> remainingLife = new MutableLiveData<>();;
    private MutableLiveData<Integer> score = new MutableLiveData<>();

    public GameViewModel (GameRepository repository) {
        this.repository = repository;
        remainingLife.setValue(3);
        score.setValue(0);
    }

    /**
     * This method collect List of words which contains english and spanish version of the words
     * and generate random boolean. Depending of the boolean, creates GameWord object which contains
     * english, spanish version of word and shows is correct translation with boolean
     *
     * @param wordList List of words which contains english and spanish version of Word
     * @return GameWord contains english, spanish version of word and isCorrect translation boolean
     */
    public GameWord getGameWord(List<Word> wordList){

        Random rand = new Random();

        int firstRandom = rand.nextInt(wordList.size());
        int secondRandom = rand.nextInt(wordList.size());

        if (firstRandom == secondRandom){
            getGameWord(wordList);
        }

        Word firstWord = wordList.get(firstRandom);
        Word secondWord = wordList.get(secondRandom);

        if (rand.nextBoolean()){
            return new GameWord(firstWord.getTextEng(),firstWord.getTextSpa(),true);
        }

        return new GameWord(firstWord.getTextEng(),secondWord.getTextSpa(),false);
    }


    public LiveData<Integer> getRemainingLife() {
        return remainingLife;
    }

    public void decreaseRemainingLife() {
        remainingLife.setValue(remainingLife.getValue()-1);
    }

    public LiveData<Integer> getScore() {
        return score;
    }

    public void increaseScore() {
        score.setValue(score.getValue()+10);
    }

}