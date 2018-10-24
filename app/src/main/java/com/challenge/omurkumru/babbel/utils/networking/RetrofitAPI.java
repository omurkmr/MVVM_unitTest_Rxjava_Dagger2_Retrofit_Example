package com.challenge.omurkumru.babbel.utils.networking;

import com.challenge.omurkumru.babbel.model.Word;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("words.json")
    Observable<List<Word>> getWords();
}
