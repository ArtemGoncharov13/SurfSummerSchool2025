package ru.adgoncharov.triviaapi.repository

import retrofit2.Response
import ru.adgoncharov.triviaapi.models.CategoryResponse
import ru.adgoncharov.triviaapi.models.Difficulty
import ru.adgoncharov.triviaapi.models.TriviaResponse
import ru.adgoncharov.triviaapi.network.RetrofitInstance

class TriviaRepository {
    suspend fun getQuestions(
        amount: Int = 5,
        categoryId: Int? = null,
        difficulty: Difficulty = Difficulty.ANY,
    ): Response<TriviaResponse> {
        return RetrofitInstance.api.getQuestions(
            amount = amount,
            category = categoryId ?: 0,
            difficulty = difficulty.value,
        )
    }

    suspend fun getCategories(): Response<CategoryResponse> {
        return RetrofitInstance.api.getCategories()
    }
}