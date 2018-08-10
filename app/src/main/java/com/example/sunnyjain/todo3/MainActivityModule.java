package com.example.sunnyjain.todo3;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    @Provides
    Resources provideMainViewModel(AppCompatActivity app) {
        return app.getResources();
    }
}
