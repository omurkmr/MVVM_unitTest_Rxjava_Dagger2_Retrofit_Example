package com.challenge.omurkumru.babbel.di;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AppModuleTest {

    Context context = mock(Context.class);

    @Test
    public void provideContextTest() {
        AppModule appModule = new AppModule(context);

        assertEquals(appModule.provideContext(),context);
    }
}