package com.example.datamanagement.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datamanagement.data.Quote

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(quote: Quote)
    @Query("DELETE FROM quote_table")
    suspend fun deleteAll()
    @Query("SELECT * FROM quote_table ORDER BY word ASC")
    fun getAlphabetizedQuote():LiveData<List<Quote>>
}