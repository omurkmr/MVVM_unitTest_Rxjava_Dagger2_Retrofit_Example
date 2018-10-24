package com.challenge.omurkumru.babbel.di;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.challenge.omurkumru.babbel.ui.entrance.EntranceRepository;
import com.challenge.omurkumru.babbel.utils.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    EntranceRepository provideEntranceRepository() {
        return new EntranceRepository();
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(Context context, EntranceRepository entranceRepository) {
        return new ViewModelFactory(context, entranceRepository);
    }
}
