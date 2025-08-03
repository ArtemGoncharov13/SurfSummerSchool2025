package ru.adgoncharov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.adgoncharov.database.models.QuestionDto
import ru.adgoncharov.database.models.QuizDto
import ru.adgoncharov.database.models.QuizWithQuestionsDto

@Dao
interface QuizDao {
    @Transaction
    @Query("SELECT * FROM quiz")
    suspend fun getAllQuizzesWithoutQuestions(): List<QuizDto>

    @Transaction
    @Query("SELECT * FROM quiz WHERE id = :quizId")
    suspend fun getQuizWithQuestions(quizId: Long): QuizWithQuestionsDto

    @Insert
    suspend fun insertQuiz(quiz: QuizDto): Long

    @Insert
    suspend fun insertQuestions(questions: List<QuestionDto>)

    @Query("DELETE FROM questions WHERE quiz_id = :quizId")
    suspend fun deleteQuestionsByQuizId(quizId: Long)

    @Query("DELETE FROM quiz WHERE id = :quizId")
    suspend fun deleteQuizById(quizId: Long)
}