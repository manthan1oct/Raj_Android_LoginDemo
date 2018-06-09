package com.questionone.data.source.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Token")
data class Token(
        @PrimaryKey
        @ColumnInfo(name = "token_id")
        val token_id: Int,
        @ColumnInfo(name = "token")
        val token: String
)