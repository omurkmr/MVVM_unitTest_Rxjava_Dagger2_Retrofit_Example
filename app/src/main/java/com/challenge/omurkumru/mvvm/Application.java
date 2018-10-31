package com.challenge.omurkumru.mvvm;

import android.content.Context;

import com.challenge.omurkumru.mvvm.di.AppComponent;
import com.challenge.omurkumru.mvvm.di.AppModule;
import com.challenge.omurkumru.mvvm.di.DaggerAppComponent;
import com.challenge.omurkumru.mvvm.di.UtilsModule;

public class Application extends android.app.Application {

    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}