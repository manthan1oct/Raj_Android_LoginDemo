package com.questionone.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.questionone.data.source.local.AppDatabase
import com.questionone.data.source.local.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(internal var applicationContext: Application) {

    @Provides
    @Singleton
    internal fun providesDatabase(): AppDatabase {
        return  Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
    }
}