package com.example.sunnyjain.todo3.di;

import com.example.sunnyjain.todo3.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Suppress("unused")
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity

}
