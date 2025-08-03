package ru.adgoncharov.database.repository

import android.util.Log
import androidx.room.Transaction
import ru.adgoncharov.database.dao.QuizDao
import ru.adgoncharov.database.mappers.toDomain
import ru.adgoncharov.database.mappers.toDto
import ru.adgoncharov.database.models.domain.Question
import ru.adgoncharov.database.models.domain.Quiz
import ru.adgoncharov.database.models.domain.QuizWithQuestions

class QuizRepositoryImpl(private val quizDao: QuizDao) : QuizRepository {
    override suspend fun getAllQuizzesWithoutQuestions(): List<Quiz> {
        return quizDao.getAllQuizzesWithoutQuestions().map { it.toDomain() }
    }

    override suspend fun getQuizWithQuestions(quizId: Long): QuizWithQuestions {
        return quizDao.getQuizWithQuestions(quizId).toDomain()
    }

    @Transaction
    override suspend fun insertQuizWithQuestions(quizWithQuestions: QuizWithQuestions): Long {
        Log.d("DB", "id//")

        val quizId = insertQuiz(quizWithQuestions.quiz.copy(id = 0))
        Log.d("DB", "id2//${quizId}")
        val questionsWithQuizId = quizWithQuestions.questions.map { question ->
            question.copy(id = 0, quizId = quizId)
        }

        insertQuestions(questionsWithQuizId)
        return quizId
    }

    suspend fun insertQuiz(quiz: Quiz): Long {
        return quizDao.insertQuiz(quiz.toDto())
    }

    suspend fun insertQuestions(questions: List<Question>) {
        quizDao.insertQuestions(questions.map { it.toDto() })
    }

    override suspend fun deleteQuestionsByQuizId(quizId: Long) {
        quizDao.deleteQuestionsByQuizId(quizId)
    }

    override suspend fun deleteQuizById(quizId: Long) {
        quizDao.deleteQuizById(quizId)
        quizDao.deleteQuestionsByQuizId(quizId)
    }

}