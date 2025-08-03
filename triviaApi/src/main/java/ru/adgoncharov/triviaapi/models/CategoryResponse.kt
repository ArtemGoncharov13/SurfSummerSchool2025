package ru.adgoncharov.triviaapi.models

import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("trivia_categories")
    val triviaCategories: List<Category>
)