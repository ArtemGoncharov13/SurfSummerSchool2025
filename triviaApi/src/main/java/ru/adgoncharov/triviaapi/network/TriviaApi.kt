package ru.adgoncharov.triviaapi.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.adgoncharov.triviaapi.models.TriviaResponse

interface TriviaApi {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = 5,
        @Query("type") type: String ="multiple"
    ): Response<TriviaResponse>
}