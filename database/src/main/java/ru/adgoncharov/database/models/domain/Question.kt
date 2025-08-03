package ru.adgoncharov.database.models.domain


data class Question(
    val id: Long = 0,
    val quizId: Long = 0,
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)