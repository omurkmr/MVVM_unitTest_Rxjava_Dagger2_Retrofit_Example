package com.challenge.omurkumru.mvvm.di;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.challenge.omurkumru.mvvm.ui.entrance.EntranceRepository;
import com.challenge.omurkumru.mvvm.ui.game.GameRepository;
import com.challenge.omurkumru.mvvm.utils.ViewModelFactory;
import com.challenge.omurkumru.mvvm.utils.networking.RetrofitAPI;
import com.challenge.omurkumru.mvvm.utils.networking.Urls;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    EntranceRepository provideEntranceRepository(RetrofitAPI retrofitAPI) {
        return new EntranceRepository(retrofitAPI);
    }

    @Provides
    @Singleton
    GameRepository provideGameRepository() {
        return new GameRepository();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return client;
    }

    @Provides
    @Singleton
    RetrofitAPI getRetrofitAPI(Retrofit retrofit) {
        return retrofit.create(RetrofitAPI.class);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(Context context, EntranceRepository entranceRepository,
                                                      GameRepository gameRepository) {
        return new ViewModelFactory(context, entranceRepository, gameRepository);
    }
}
