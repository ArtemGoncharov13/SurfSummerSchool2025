package ru.adgoncharov.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo("quiz_id")
    val quizId: Long,

    @ColumnInfo("category")
    val category: String,

    @ColumnInfo("type")
    val type: String,

    @ColumnInfo("difficulty")
    val difficulty: String,

    @ColumnInfo("question")
    val question: String,

    @ColumnInfo("correct_answer")
    val correctAnswer: String,

    @ColumnInfo("incorrect_answers")
    val incorrectAnswers: List<String>
)