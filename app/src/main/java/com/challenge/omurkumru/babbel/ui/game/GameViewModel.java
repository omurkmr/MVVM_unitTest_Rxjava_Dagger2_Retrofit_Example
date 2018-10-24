package com.challenge.omurkumru.babbel.ui.game;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.Random;

public class GameViewModel extends ViewModel {

    private GameRepository repository;

    public GameViewModel (GameRepository repository) {
        this.repository = repository;
    }

}
