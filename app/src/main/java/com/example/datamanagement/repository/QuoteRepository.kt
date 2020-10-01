package com.example.datamanagement.repository

import com.example.datamanagement.dao.QuoteDao
import com.example.datamanagement.data.Quote

class QuoteRepository(private val quoteDao: QuoteDao) {

    val allQuotes=quoteDao.getAlphabetizedQuote()
     suspend fun insert(quote: Quote){
         quoteDao.insert(quote)
     }

}