package com.example.android.kotlinretrofit.application

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.android.kotlinretrofit.api.QuoteService
import com.example.android.kotlinretrofit.api.RetrofitHelper
import com.example.android.kotlinretrofit.repository.QuotesRepository
import com.example.android.kotlinretrofit.room_database.QuoteDatabase
import com.example.android.kotlinretrofit.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quotesRepository: QuotesRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,60,TimeUnit.MINUTES).
                setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)

    }

    private fun initialize() {

        val quoteDatabase = QuoteDatabase.getInstance(applicationContext)
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        quotesRepository = QuotesRepository(quoteService , quoteDatabase , applicationContext )
    }
}