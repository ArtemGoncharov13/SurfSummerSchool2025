package ru.adgoncharov.surfsummerschool2025.mappers

import ru.adgoncharov.triviaapi.models.QuestionModel
import ru.adgoncharov.database.models.domain.Question

fun QuestionModel.toDomainQuestion(quizId: Long = 0L): Question {
    return Question(
        id = 0L,
        quizId = quizId,
        category = this.category,
        type = this.type,
        difficulty = this.difficulty,
        question = this.question,
        correctAnswer = this.correctAnswer,
        incorrectAnswers = this.incorrectAnswers
    )
}