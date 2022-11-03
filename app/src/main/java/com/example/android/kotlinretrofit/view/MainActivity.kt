package com.example.android.kotlinretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.kotlinretrofit.application.QuoteApplication
import com.example.android.kotlinretrofit.R
import com.example.android.kotlinretrofit.Response
import com.example.android.kotlinretrofit.view_models.MainVewModel
import com.example.android.kotlinretrofit.view_models.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainVewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quotesRepository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository))[MainVewModel::class.java]

        mainViewModel.quotes.observe(this, Observer {
            when(it){
                is Response.Loading -> {}
                is  Response.Success ->{
                    it.data.let{
                        if (it != null) {
                            Toast.makeText(this@MainActivity,it.results.size.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                }
                is  Response.Error ->{
                    it.errorMessage
                        Toast.makeText(this@MainActivity,it.errorMessage.toString(),Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}