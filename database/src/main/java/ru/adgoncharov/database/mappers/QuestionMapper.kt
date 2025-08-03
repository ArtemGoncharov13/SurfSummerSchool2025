package ru.adgoncharov.database.mappers

import ru.adgoncharov.database.models.QuestionDto
import ru.adgoncharov.database.models.domain.Question

fun QuestionDto.toDomain(): Question = Question(
    id = id,
    quizId = quizId,
    category = category,
    type = type,
    difficulty = difficulty,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers
)

fun Question.toDto(): QuestionDto = QuestionDto(
    id = id,
    quizId = quizId,
    category = category,
    type = type,
    difficulty = difficulty,
    question = question,
    correctAnswer = correctAnswer,
    incorrectAnswers = incorrectAnswers
)