package com.example.sunnyjain.todo3.di;

import com.example.sunnyjain.todo3.MainActivity;
import com.example.sunnyjain.todo3.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
