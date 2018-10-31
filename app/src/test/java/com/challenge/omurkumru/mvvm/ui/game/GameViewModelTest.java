package com.challenge.omurkumru.mvvm.ui.game;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.challenge.omurkumru.mvvm.model.GameWord;
import com.challenge.omurkumru.mvvm.model.Word;
import com.challenge.omurkumru.mvvm.utils.WordListProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GameViewModelTest {

    GameViewModel viewModel;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private MutableLiveData<Integer> remainingLife;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new GameViewModel(gameRepository);
    }

    @Test
    public void getGameWordGivenWordListIsValidWhenGettingCorrectWordThenGetWord() { //Given When Then Clause

        List<Word> wordList = WordListProvider.createListProvider();

        // Trigger
        GameWord result = viewModel.getGameWord(wordList);
        String firstWordEn = result.getEnWord();  //one
        String firstdWordSp = result.getEspWord();  //uno

        if (result.isTrue()) {

            wordList.forEach(x -> {
                if (x.getTextEng().equals(firstWordEn) && x.getTextSpa().equals(firstdWordSp)) {
                    assertTrue(x.getTextEng().equals(firstWordEn) && x.getTextSpa().equals(firstdWordSp));
                }

            });
        } else if (!result.isTrue()) {

            wordList.forEach(x -> {
                if (x.getTextEng().equals(firstWordEn) && !x.getTextSpa().equals(firstdWordSp)) {
                    assertTrue(x.getTextEng().equals(firstWordEn) && !x.getTextSpa().equals(firstdWordSp));
                }
            });
        }

        //assertEquals
        assertNotNull(result);
    }

    @Test
    public void getRemainingLife() {
        LiveData<Integer> result = viewModel.getRemainingLife();
        assertNotNull(result.getValue());
    }

    @Test
    public void decreaseRemainingLife() {

        LiveData<Integer> previousLife = viewModel.getRemainingLife();
        int firstLife = previousLife.getValue();

        viewModel.decreaseRemainingLife();
        LiveData<Integer> afterLife = viewModel.getRemainingLife();
        int secondLife = afterLife.getValue();

        assertNotEquals(firstLife, secondLife);
    }


    @Test
    public void getScore() {
        LiveData<Integer> result = viewModel.getScore();
        assertNotNull(result.getValue());
    }

    @Test
    public void increaseScore() {
        LiveData<Integer> previousScore = viewModel.getScore();
        int firstScore = previousScore.getValue();

        viewModel.increaseScore();
        LiveData<Integer> afterScore = viewModel.getScore();
        int secondScore = afterScore.getValue();

        assertNotEquals(firstScore, secondScore);
    }
}