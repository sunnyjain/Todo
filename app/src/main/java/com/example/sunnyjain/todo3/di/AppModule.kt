package com.example.sunnyjain.todo3.di

import android.app.Application;
import android.arch.persistence.room.Room
import android.content.Context;
import com.example.sunnyjain.todo3.db.TaskDB
import com.example.sunnyjain.todo3.db.TaskDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): TaskDB {
        return Room
                .databaseBuilder(app, TaskDB::class.java, "tasks.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDB): TaskDao {
        return db.taskDao()
    }
}
