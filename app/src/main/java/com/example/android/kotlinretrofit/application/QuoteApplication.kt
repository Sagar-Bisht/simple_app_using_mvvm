package com.example.android.kotlinretrofit.application

import android.app.Application
import com.example.android.kotlinretrofit.api.QuoteService
import com.example.android.kotlinretrofit.api.RetrofitHelper
import com.example.android.kotlinretrofit.repository.QuotesRepository
import com.example.android.kotlinretrofit.room_database.QuoteDatabase

class QuoteApplication : Application() {

    lateinit var quotesRepository: QuotesRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {

        val quoteDatabase = QuoteDatabase.getInstance(applicationContext)
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        quotesRepository = QuotesRepository(quoteService , quoteDatabase , applicationContext )
    }
}