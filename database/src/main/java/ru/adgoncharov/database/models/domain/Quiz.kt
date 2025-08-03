package ru.adgoncharov.database.models.domain

import java.util.Date

data class Quiz(
    val id: Long = 0,
    val correctAnswer: Int = 0,
    val totalQuestion: Int = 0,
    val answers: List<String?> = listOf<String?>(),
    val shuffledAnswers: List<List<String>> = listOf<List<String>>(),
    val date: Date = Date(),
    val category: String = "",
    val difficulty: String = "",
)