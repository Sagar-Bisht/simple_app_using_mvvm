package com.example.android.kotlinretrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.kotlinretrofit.api.QuoteService
import com.example.android.kotlinretrofit.models.QuoteResponse
import com.example.android.kotlinretrofit.room_database.QuoteDatabase
import com.example.android.kotlinretrofit.utils.NetworkUtils

class QuotesRepository(private val quoteService: QuoteService,
                       private val quoteDatabase: QuoteDatabase,
                       private val context : Context) {
    private val quotesLiveData = MutableLiveData<QuoteResponse>()

    val quotes:LiveData<QuoteResponse>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        if(NetworkUtils.isOnline(context)){
            val result = quoteService.getQuotes(page)
            if (result.body() != null) {
                quoteDatabase.quoteDao().insertQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }else{
            val quotes = quoteDatabase.quoteDao().getQuotesFromRoom()
            val quoteList = QuoteResponse(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }
    }
}
