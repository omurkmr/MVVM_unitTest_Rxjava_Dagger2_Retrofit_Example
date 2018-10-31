package com.challenge.omurkumru.mvvm.di;

import com.challenge.omurkumru.mvvm.ui.entrance.EntranceFragment;
import com.challenge.omurkumru.mvvm.ui.game.GameFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void inject(EntranceFragment fragment);
    void inject(GameFragment fragment);

}
