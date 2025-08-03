package ru.adgoncharov.database.models.domain


data class QuizWithQuestions(
    val quiz: Quiz = Quiz(),
    val questions: List<Question> = listOf<Question>()
)