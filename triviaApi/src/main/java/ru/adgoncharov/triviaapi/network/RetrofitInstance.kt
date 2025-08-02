package ru.adgoncharov.triviaapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TriviaApi by lazy {
        retrofit.create(TriviaApi::class.java)
    }
}