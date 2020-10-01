package com.example.datamanagement.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.datamanagement.data.Quote
import com.example.datamanagement.database.getSingleDataBase
import com.example.datamanagement.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

class QuoteViewModel(application: Application) :AndroidViewModel(application) {
    private var repository:QuoteRepository
    val allQuotes:LiveData<List<Quote>>
    init {
        val quoteDao= getSingleDataBase(application,viewModelScope).quoteDao
        repository=QuoteRepository(quoteDao)
        allQuotes=repository.allQuotes
    }

    fun insert(quote: Quote)= viewModelScope.launch(Dispatchers.IO){
        repository.insert(quote)
    }


}