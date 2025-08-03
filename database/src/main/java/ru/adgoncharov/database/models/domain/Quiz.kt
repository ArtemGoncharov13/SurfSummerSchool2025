package ru.adgoncharov.database.models.domain

import java.util.Date

data class Quiz(
    val id: Long = 0,
    val correctAnswer: Int,
    val totalQuestion: Int,
    val answers: List<String?>,
    val shuffledAnswers: List<List<String>>,
    val date: Date,
    val category: String,
    val difficulty: String,
)