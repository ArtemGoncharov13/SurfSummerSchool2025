package ru.adgoncharov.surfsummerschool2025.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.adgoncharov.database.models.domain.Quiz
import ru.adgoncharov.database.models.domain.QuizWithQuestions
import ru.adgoncharov.database.repository.QuizRepository
import ru.adgoncharov.surfsummerschool2025.state.HistoryScreenState

class HistoryScreenViewModel(
    private val quizRepository: QuizRepository
): ViewModel() {

    private val _state = MutableStateFlow<HistoryScreenState>(HistoryScreenState.Idle)
    val state: StateFlow<HistoryScreenState> = _state

    private val _quizzes = MutableStateFlow<List<Quiz>>(emptyList())
    val quizzes: StateFlow<List<Quiz>> = _quizzes.asStateFlow()

    private val _selectedQuiz = MutableStateFlow<QuizWithQuestions?>(null)
    val selectedQuiz: StateFlow<QuizWithQuestions?> = _selectedQuiz.asStateFlow()

    fun loadAllQuizzes() {
        _state.value = HistoryScreenState.Loading
        viewModelScope.launch {
            _quizzes.value = quizRepository.getAllQuizzesWithoutQuestions()
        }
        _state.value = HistoryScreenState.Success
    }

    suspend fun loadQuizById(quizId: Long) {
        _state.value = HistoryScreenState.Loading
        _selectedQuiz.value = viewModelScope.async {
            quizRepository.getQuizWithQuestions(quizId)
        }.await()
        _state.value = HistoryScreenState.Success
    }

    fun deleteQuizById(quizId: Long) {
        viewModelScope.launch {
            quizRepository.deleteQuizById(quizId)
            loadAllQuizzes()
        }
    }

    fun insertQuizWithQuestions(quizWithQuestions: QuizWithQuestions) {
        viewModelScope.launch {
            quizRepository.insertQuizWithQuestions(quizWithQuestions)
            loadAllQuizzes()
        }
    }

    fun clearSelectedQuiz() {
        _selectedQuiz.value = null
    }

}