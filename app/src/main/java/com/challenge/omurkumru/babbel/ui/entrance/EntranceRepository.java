package com.challenge.omurkumru.babbel.ui.entrance;

import com.challenge.omurkumru.babbel.model.Word;
import com.challenge.omurkumru.babbel.utils.networking.RetrofitAPI;

import java.util.List;

import io.reactivex.Observable;

public class EntranceRepository {

    private RetrofitAPI retrofitAPI;

    public EntranceRepository(RetrofitAPI retrofitAPI) {
        this.retrofitAPI = retrofitAPI;
    }

    public Observable<List<Word>> getWordList() {
        return retrofitAPI.getWords();
    }

}