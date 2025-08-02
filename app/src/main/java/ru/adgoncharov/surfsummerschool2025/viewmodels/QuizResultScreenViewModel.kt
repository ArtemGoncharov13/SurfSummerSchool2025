package ru.adgoncharov.surfsummerschool2025.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer
import ru.adgoncharov.triviaapi.models.Question

class QuizResultScreenViewModel : ViewModel() {
    private lateinit var questions: List<Question>

    private val _correctAnswersCount = MutableStateFlow(0)
    val correctAnswerCount: StateFlow<Int> = _correctAnswersCount

    private val _answers = MutableStateFlow<List<String?>>(listOf<String?>())
    val answers: StateFlow<List<String?>> = _answers

    private val _shuffledAnswer = MutableStateFlow<List<List<String>>>(listOf())

    fun getResults(questions: List<Question>, allShuffledAnswer: List<List<String>>, answers: List<String?>) {
        this.questions = questions
        _answers.value = answers
        _shuffledAnswer.value = allShuffledAnswer
        countCorrectAnswer()
    }

    private fun countCorrectAnswer() {
        _correctAnswersCount.value = 0;
        for (i in 0..< questions.size) {
            if (answers.value[i].equals(questions[i].correctAnswer))
                _correctAnswersCount.value++
        }
    }

    fun totalQuestions(): Int = questions.size
}