package ru.adgoncharov.triviaapi.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.adgoncharov.triviaapi.models.CategoryResponse
import ru.adgoncharov.triviaapi.models.TriviaResponse

interface TriviaApi {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = 5,
        @Query("type") type: String = "multiple",
        @Query("category") category: Int = 0,
        @Query("difficulty") difficulty: String = "",
    ): Response<TriviaResponse>

    @GET("api_category.php")
    suspend fun getCategories(): Response<CategoryResponse>
}