package ru.adgoncharov.database.models.domain


data class QuizWithQuestions(
    val quiz: Quiz,
    val questions: List<Question>
)