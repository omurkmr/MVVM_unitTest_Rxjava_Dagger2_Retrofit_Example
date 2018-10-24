package com.challenge.omurkumru.babbel.di;

import com.challenge.omurkumru.babbel.ui.entrance.EntranceRepository;
import com.challenge.omurkumru.babbel.ui.game.GameRepository;
import com.challenge.omurkumru.babbel.utils.networking.RetrofitAPI;
import com.challenge.omurkumru.babbel.utils.networking.Urls;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilsModuleTest {

    UtilsModule utilsModule;
    Gson gson;

    @Mock
    OkHttpClient client;

    Retrofit retrofit;

    @Before
    public void setUp() {
        utilsModule = new UtilsModule();
        gson = utilsModule.provideGson();
        retrofit = utilsModule.provideRetrofit(gson,client);
    }

    @Test
    public void provideGsonTest() {
        Gson gson = utilsModule.provideGson();
        assertNotNull(gson);
    }

    @Test
    public void provideRetrofit() {
        Retrofit retrofit = utilsModule.provideRetrofit(gson,client);
        assertNotNull(retrofit);

        String url = Urls.BASE_URL;

        assertEquals(retrofit.baseUrl().toString(),url);
    }

    @Test
    public void getRetrofitApi() {
        RetrofitAPI retrofitAPI = utilsModule.getRetrofitAPI(retrofit);

        assertNotNull(retrofitAPI.getWords());
    }

    @Test
    public void getRequestHeader() {

        OkHttpClient okHttpClient = utilsModule.getRequestHeader();
        assertNotNull(okHttpClient.interceptors());
    }

    @Test
    public void provideEntranceRepository() {
        Assert.assertEquals(utilsModule.provideEntranceRepository(utilsModule.getRetrofitAPI(retrofit)).getClass().getName(), EntranceRepository.class.getName());
    }

    @Test
    public void provideGameRepository() {
        Assert.assertEquals(utilsModule.provideGameRepository().getClass().getName(), GameRepository.class.getName());
    }
}