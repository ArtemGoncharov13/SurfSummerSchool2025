package ru.adgoncharov.surfsummerschool2025.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.adgoncharov.database.models.domain.QuizWithQuestions
import ru.adgoncharov.database.models.domain.Question

class QuizResultScreenViewModel : ViewModel() {
    private val _quizWithQuestions = MutableStateFlow<QuizWithQuestions>(QuizWithQuestions())
    val quizWithQuestions: StateFlow<QuizWithQuestions> = _quizWithQuestions.asStateFlow()

    fun getResults(quizWithQuestions: QuizWithQuestions) {
        _quizWithQuestions.value = _quizWithQuestions.value.copy(quiz = quizWithQuestions.quiz, questions = quizWithQuestions.questions)
    }

    fun getQuestion(questionIndex: Int): Question {
        return _quizWithQuestions.value.questions[questionIndex]
    }

    fun getAnswer(answerIndex: Int) : String? {
        return _quizWithQuestions.value.quiz.answers[answerIndex]
    }

    fun getAllShuffledAnswers() : List<List<String>> {
        return _quizWithQuestions.value.quiz.shuffledAnswers
    }

    fun getShuffledAnswers(questionIndex: Int) : List<String> {
        return  _quizWithQuestions.value.quiz.shuffledAnswers[questionIndex]
    }

    fun getCorrectAnswersCount(): Int {
        return _quizWithQuestions.value.quiz.correctAnswer
    }

    fun getCategory(): String {
        return _quizWithQuestions.value.quiz.category
    }

    fun getDifficulty(): String {
        return _quizWithQuestions.value.quiz.difficulty
    }

    fun totalQuestions(): Int = _quizWithQuestions.value.quiz.totalQuestion
}