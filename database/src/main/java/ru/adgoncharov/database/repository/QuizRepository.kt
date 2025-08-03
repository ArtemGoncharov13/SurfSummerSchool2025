package ru.adgoncharov.database.repository

import ru.adgoncharov.database.models.domain.Question
import ru.adgoncharov.database.models.domain.Quiz
import ru.adgoncharov.database.models.domain.QuizWithQuestions

interface QuizRepository {

    suspend fun getAllQuizzesWithoutQuestions(): List<Quiz>

    suspend fun getQuizWithQuestions(quizId: Long): QuizWithQuestions

    suspend fun insertQuizWithQuestions(quizWithQuestions: QuizWithQuestions): Long

    suspend fun deleteQuestionsByQuizId(quizId: Long)

    suspend fun deleteQuizById(quizId: Long)
}