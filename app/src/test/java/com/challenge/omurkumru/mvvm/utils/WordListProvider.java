package com.challenge.omurkumru.mvvm.utils;

import com.challenge.omurkumru.mvvm.model.Word;

import java.util.ArrayList;
import java.util.List;

public class WordListProvider {

    public static List<Word> createListProvider() {

        ArrayList<Word> wordList = new ArrayList<>();

        Word word1 = new Word("one","uno");
        Word word2 = new Word("two","dos");
        Word word3 = new Word("three","tres");

        wordList.add(word1);
        wordList.add(word2);
        wordList.add(word3);

        return wordList;
    }

}
