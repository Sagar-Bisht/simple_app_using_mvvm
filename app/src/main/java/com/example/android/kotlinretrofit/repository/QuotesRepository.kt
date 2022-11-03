package com.example.android.kotlinretrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.kotlinretrofit.Response
import com.example.android.kotlinretrofit.api.QuoteService
import com.example.android.kotlinretrofit.models.QuoteResponse
import com.example.android.kotlinretrofit.room_database.QuoteDatabase
import com.example.android.kotlinretrofit.utils.NetworkUtils

class QuotesRepository(private val quoteService: QuoteService,
                       private val quoteDatabase: QuoteDatabase,
                       private val context : Context) {
    private val quotesLiveData = MutableLiveData<Response<QuoteResponse>>()

    val quotes:LiveData<Response<QuoteResponse>>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        if(NetworkUtils.isOnline(context)){
        try {
                val result = quoteService.getQuotes(page)
                if (result.body() != null) {
                    quoteDatabase.quoteDao().insertQuotes(result.body()!!.results)
                    quotesLiveData.postValue(Response.Success(result.body()!!))
                }else{
                    quotesLiveData.postValue(Response.Error("Error Occurred"))
                }
            }catch (e : Exception){
                quotesLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
       else{
            val quotes = quoteDatabase.quoteDao().getQuotesFromRoom()
            val quoteList = QuoteResponse(1,1,1,quotes,1,1)
            quotesLiveData.postValue(Response.Success(quoteList))
        }
    }

    suspend fun getQuotesBackground(){
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomNumber)
        if(result.body() !=null){
            quoteDatabase.quoteDao().insertQuotes(result.body()!!.results)
        }
    }
}
