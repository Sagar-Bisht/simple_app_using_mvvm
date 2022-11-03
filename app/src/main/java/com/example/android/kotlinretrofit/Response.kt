package com.example.android.kotlinretrofit

import com.example.android.kotlinretrofit.models.QuoteResponse

/**
 * simple class
 */
//sealed class Response(val data : QuoteResponse? = null ,val errorMessage : String? = null){
//    class Loading() : Response()
//    class Success(data : QuoteResponse) : Response(data = data)
//    class Error(errorMessage: String) : Response(errorMessage = errorMessage)
//}

/**
 * generic class
 */
sealed class Response<T>(val data : T? = null ,val errorMessage : String? = null){
    class Loading<T>() : Response<T>()
    class Success<T>(data : T) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}
