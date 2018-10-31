package com.challenge.omurkumru.mvvm.ui.entrance;

import com.challenge.omurkumru.mvvm.model.Word;
import com.challenge.omurkumru.mvvm.utils.networking.RetrofitAPI;

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