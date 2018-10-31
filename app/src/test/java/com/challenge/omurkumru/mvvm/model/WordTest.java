package com.challenge.omurkumru.mvvm.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WordTest {

    @Test
    public void toStringTest() {

        Word word = new Word("one","uno");

        String expected = "Word{" +
                "english ='" + word.getTextEng() + '\'' +
                ", spanish ='" + word.getTextSpa() + '\'' +
                '}';

        assertEquals(word.toString(),expected);
    }
}