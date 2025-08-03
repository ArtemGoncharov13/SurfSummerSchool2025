package ru.adgoncharov.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.adgoncharov.database.converters.Converters
import ru.adgoncharov.database.dao.QuizDao
import ru.adgoncharov.database.models.QuestionDto
import ru.adgoncharov.database.models.QuizDto

const val DATABASE_VERSION_CODE = 3

@Database(entities = [QuizDto::class, QuestionDto::class], version = DATABASE_VERSION_CODE)
@TypeConverters(Converters::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                ).build().also { INSTANCE = it }
            }
        }

    }
}