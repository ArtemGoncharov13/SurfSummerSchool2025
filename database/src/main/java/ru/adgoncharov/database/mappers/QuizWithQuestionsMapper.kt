package ru.adgoncharov.database.mappers

import ru.adgoncharov.database.models.QuizWithQuestionsDto
import ru.adgoncharov.database.models.domain.QuizWithQuestions

fun QuizWithQuestionsDto.toDomain(): QuizWithQuestions = QuizWithQuestions(
    quiz = quiz.toDomain(),
    questions = question.map { it.toDomain() }
)

fun QuizWithQuestions.toDto(): QuizWithQuestionsDto = QuizWithQuestionsDto(
    quiz = quiz.toDto(),
    question = questions.map { it.toDto() }
)