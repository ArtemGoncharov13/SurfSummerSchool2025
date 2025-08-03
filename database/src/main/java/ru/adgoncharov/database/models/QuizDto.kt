package ru.adgoncharov.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "quiz")
data class QuizDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "correct_answer")
    val correctAnswer: Int,

    @ColumnInfo(name = "total_questions")
    val totalQuestion: Int,

    @ColumnInfo(name = "answers")
    val answers: List<String?>,

    @ColumnInfo(name = "shuffled_answers")
    val shuffledAnswers: List<List<String>>,

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "difficulty")
    val difficulty: String,
)