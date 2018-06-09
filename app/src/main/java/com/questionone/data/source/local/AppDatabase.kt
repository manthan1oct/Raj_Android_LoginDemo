package com.questionone.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

const val DB_NAME = "master.sqlite"
const val DB_VERSION = 1

@Database(entities = [Token::class], version = DB_VERSION)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getTokenDao() : TokenDao
}




