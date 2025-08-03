package ru.adgoncharov.surfsummerschool2025.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.adgoncharov.database.models.domain.Question
import ru.adgoncharov.database.models.domain.QuizWithQuestions
import java.util.Date

class QuizScreenViewModel : ViewModel() {
    private val _quizWithQuestions = MutableStateFlow<QuizWithQuestions>(QuizWithQuestions())
    val quizWithQuestions: StateFlow<QuizWithQuestions> = _quizWithQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    private val _selectedAnswer = MutableStateFlow<String?>(null)
    val selectedAnswer: StateFlow<String?> = _selectedAnswer

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished

    fun getAnswers(): List<String?> {
        return _quizWithQuestions.value.quiz.answers
    }

    fun startQuiz(questions: List<Question>, category: String = "Все категории", difficulty: String = "Любая") {
        Log.d("QuizScreen", "size questions from QuizScreenVM: ${questions.size}")
        _quizWithQuestions.value = _quizWithQuestions.value.copy(
            quiz = _quizWithQuestions.value.quiz.copy(
                totalQuestion = questions.size,
                category = category,
                difficulty = difficulty,
                correctAnswer = 0,
                date = Date(),
                answers = List<String?>(questions.size) {null}
            ),
            questions = questions
        )
        Log.d(
            "QuizScreen",
            "size questions in _quizWith from QuizScreenVM: ${_quizWithQuestions.value.questions.size}"
        )
        shuffleAllAnswers()
        _currentQuestionIndex.value = 0
        _selectedAnswer.value = null
        _quizFinished.value = false
    }

    fun getCurrentQuestion(): Question? {
        return if (_currentQuestionIndex.value < _quizWithQuestions.value.quiz.totalQuestion)
            _quizWithQuestions.value.questions[_currentQuestionIndex.value]
        else
            null
    }

    fun selectAnswer(answer: String) {
        _selectedAnswer.value = answer
    }

    private fun moveToNextQuestion() {
        Log.d(
            "QuizScreen",
            "Number current question: ${_currentQuestionIndex.value} from ${_quizWithQuestions.value.quiz.totalQuestion - 1}"
        )
        if (_currentQuestionIndex.value < _quizWithQuestions.value.quiz.totalQuestion - 1) {
            _currentQuestionIndex.value += 1
            _selectedAnswer.value = null
        } else {
            _quizFinished.value = true
        }
    }

    fun isQuizFinished(): Boolean {
        return _currentQuestionIndex.value >= _quizWithQuestions.value.quiz.totalQuestion
    }

    fun totalQuestions(): Int = _quizWithQuestions.value.quiz.totalQuestion

    fun provideAnswer() {
        Log.d("QuizScreen", "ProvideAnswer")
        val currentQuestion = getCurrentQuestion() ?: return
        val selected = _selectedAnswer.value ?: return
        Log.d("QuizScreen", "ProvideAnswer1")
        val answers = _quizWithQuestions.value.quiz.answers.toMutableList()
        Log.d("QuizScreen", "ProvideAnswer1.2")
        answers[_currentQuestionIndex.value] = selected
        Log.d("QuizScreen", "ProvideAnswer2") ////////////////////////////////// ТУТ КРАШ
        _quizWithQuestions.value =
            _quizWithQuestions.value.copy(quiz = _quizWithQuestions.value.quiz.copy(answers = answers))
        Log.d("QuizScreen", "ProvideAnswer3")
        if (selected == currentQuestion.correctAnswer) {
            _quizWithQuestions.value = _quizWithQuestions.value.copy(
                quiz = _quizWithQuestions.value.quiz.copy(correctAnswer = (_quizWithQuestions.value.quiz.correctAnswer + 1))
            )
        }
        Log.d("QuizScreen", "ProvideAnswer4")

        moveToNextQuestion()
    }

    fun resetQuiz() {
        _quizFinished.value = false
    }

    private fun shuffleAllAnswers() {
        val shuffledAnswers: MutableList<List<String>> = mutableListOf()
        for (question in _quizWithQuestions.value.questions) {
            shuffledAnswers.add((question.incorrectAnswers + listOf(question.correctAnswer)).shuffled())
        }
        _quizWithQuestions.value = _quizWithQuestions.value.copy(
            quiz = _quizWithQuestions.value.quiz.copy(
                shuffledAnswers = shuffledAnswers.toList()
            )
        )
    }

    fun getShuffledAnswers(): List<String> {
        return _quizWithQuestions.value.quiz.shuffledAnswers[_currentQuestionIndex.value]
    }

    fun getAllShuffledAnswers(): List<List<String>> {
        return _quizWithQuestions.value.quiz.shuffledAnswers
    }

    fun getCorrectAnswersCount(): Int {
        return _quizWithQuestions.value.quiz.correctAnswer
    }

    fun getQuestion(questionIndex: Int): Question {
        return _quizWithQuestions.value.questions[questionIndex]
    }
}