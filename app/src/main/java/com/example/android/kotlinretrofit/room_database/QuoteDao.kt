package com.example.android.kotlinretrofit.room_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.kotlinretrofit.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuotes(quote: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotesFromRoom() : List<Result>

}