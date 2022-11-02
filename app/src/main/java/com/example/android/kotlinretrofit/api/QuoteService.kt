package com.example.android.kotlinretrofit.api

import com.example.android.kotlinretrofit.models.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page : Int) : Response<QuoteResponse>
}