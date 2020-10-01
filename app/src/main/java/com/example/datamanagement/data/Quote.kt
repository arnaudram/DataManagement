package com.example.datamanagement.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="quote_table")
data class Quote(
    @PrimaryKey

    @ColumnInfo(name="word")
    var word:String
)
