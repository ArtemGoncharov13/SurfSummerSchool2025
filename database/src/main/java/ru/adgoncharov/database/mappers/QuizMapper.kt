package ru.adgoncharov.database.mappers

import ru.adgoncharov.database.models.QuizDto
import ru.adgoncharov.database.models.domain.Quiz

fun QuizDto.toDomain(): Quiz = Quiz(
    id = id,
    correctAnswer = correctAnswer,
    totalQuestion = totalQuestion,
    answers = answers,
    shuffledAnswers = shuffledAnswers,
    category = category,
    difficulty = difficulty,
    date = date
)

fun Quiz.toDto(): QuizDto = QuizDto(
    id = id,
    correctAnswer = correctAnswer,
    totalQuestion = totalQuestion,
    answers = answers,
    shuffledAnswers = shuffledAnswers,
    category = category,
    difficulty = difficulty,
    date = date
)