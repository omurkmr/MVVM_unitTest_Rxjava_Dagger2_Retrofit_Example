package com.challenge.omurkumru.mvvm.utils.networking;

import com.challenge.omurkumru.mvvm.model.Word;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("words.json")
    Observable<List<Word>> getWords();
}
