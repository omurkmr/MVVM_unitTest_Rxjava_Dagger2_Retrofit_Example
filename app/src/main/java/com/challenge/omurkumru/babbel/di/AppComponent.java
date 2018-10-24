package com.challenge.omurkumru.babbel.di;

import com.challenge.omurkumru.babbel.ui.entrance.EntranceFragment;
import com.challenge.omurkumru.babbel.ui.game.GameFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void inject(EntranceFragment fragment);
    void inject(GameFragment fragment);

}
