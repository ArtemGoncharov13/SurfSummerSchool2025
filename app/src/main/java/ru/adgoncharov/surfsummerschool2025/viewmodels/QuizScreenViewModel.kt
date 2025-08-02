package ru.adgoncharov.surfsummerschool2025.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.adgoncharov.triviaapi.models.Question

class QuizScreenViewModel : ViewModel() {

    private lateinit var questions: List<Question>

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    private val _selectedAnswer = MutableStateFlow<String?>(null)
    val selectedAnswer: StateFlow<String?> = _selectedAnswer

    private val _answers = MutableStateFlow<MutableList<String?>>(mutableListOf<String?>())
    val answers: StateFlow<MutableList<String?>> = _answers

    private val _correctAnswersCount = MutableStateFlow(0)
    val correctAnswerCount: StateFlow<Int> = _correctAnswersCount

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished

    private val _shuffledQuestions = MutableStateFlow<List<List<String>>>(listOf())

    fun getAnswers(): List<String?> {
        return _answers.value
    }

    fun startQuiz(questions: List<Question>) {
        this.questions = questions
        Log.d("QuizScreen", "size: ${this.questions.size}")
        _currentQuestionIndex.value = 0
        _correctAnswersCount.value = 0
        _selectedAnswer.value = null
        _answers.value = MutableList(questions.size) { null }
        shuffleAllAnswers()
        _quizFinished.value = false
    }

    fun getCurrentQuestion(): Question? {
        return if (this::questions.isInitialized && _currentQuestionIndex.value < questions.size)
            questions[_currentQuestionIndex.value]
        else
            null
    }

    fun selectAnswer(answer: String) {
        _selectedAnswer.value = answer
    }

    private fun moveToNextQuestion() {
        if (_currentQuestionIndex.value < questions.size - 1) {
            _currentQuestionIndex.value += 1
            _selectedAnswer.value = null
        } else {
            _quizFinished.value = true
        }
    }

    fun isQuizFinished(): Boolean {
        return _currentQuestionIndex.value >= questions.size
    }

    fun totalQuestions(): Int = questions.size

    fun provideAnswer() {
        val currentQuestion = getCurrentQuestion() ?: return
        val selected = _selectedAnswer.value ?: return

        _answers.value[_currentQuestionIndex.value] = selected
        if (selected == currentQuestion.correctAnswer) {
            _correctAnswersCount.value += 1
        }

        moveToNextQuestion()
    }

    fun resetQuiz() {
        _quizFinished.value = false
    }

    private fun shuffleAllAnswers() {
        val shuffledQuestion: MutableList<List<String>> = mutableListOf()
        for (question in questions) {
            shuffledQuestion.add((question.incorrectAnswers + listOf(question.correctAnswer)).shuffled())
        }
        _shuffledQuestions.value = shuffledQuestion.toList()
    }

    fun getShuffledAnswers(): List<String> {
        return _shuffledQuestions.value[_currentQuestionIndex.value]
    }

    fun getAllShuffledAnswers(): List<List<String>> {
        return _shuffledQuestions.value
    }
}