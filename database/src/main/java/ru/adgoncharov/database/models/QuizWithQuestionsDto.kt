package ru.adgoncharov.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class QuizWithQuestionsDto(
    @Embedded val quiz: QuizDto,

    @Relation(
        parentColumn = "id",
        entityColumn = "quiz_id"
    )
    val question: List<QuestionDto>
)