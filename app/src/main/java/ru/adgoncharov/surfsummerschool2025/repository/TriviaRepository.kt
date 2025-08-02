package ru.adgoncharov.surfsummerschool2025.repository

import ru.adgoncharov.triviaapi.models.TriviaResponse
import ru.adgoncharov.triviaapi.network.RetrofitInstance
import ru.adgoncharov.triviaapi.network.TriviaApi
import retrofit2.Response

class TriviaRepository {
    suspend fun getQuestions(amount: Int = 5): Response<TriviaResponse> {
        return RetrofitInstance.api.getQuestions(amount)
    }
}