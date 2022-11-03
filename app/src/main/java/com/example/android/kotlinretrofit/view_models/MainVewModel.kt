package com.example.android.kotlinretrofit.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.kotlinretrofit.Response
import com.example.android.kotlinretrofit.models.QuoteResponse
import com.example.android.kotlinretrofit.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainVewModel(private val quoteRepository: QuotesRepository ) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotes(1)
        }
    }

    val quotes : LiveData<Response<QuoteResponse>>
        get() = quoteRepository.quotes
    
}