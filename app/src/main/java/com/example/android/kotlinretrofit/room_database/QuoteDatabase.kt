package com.example.android.kotlinretrofit.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.kotlinretrofit.models.Result


@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {

        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {
            synchronized(this) {
            if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, QuoteDatabase::class.java, "quoteDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}