package com.challenge.omurkumru.babbel.di;

import com.challenge.omurkumru.babbel.ui.entrance.EntranceFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void inject(EntranceFragment fragment);

}
