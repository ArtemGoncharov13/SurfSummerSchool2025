package ru.adgoncharov.surfsummerschool2025.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.adgoncharov.database.models.domain.QuizWithQuestions
import ru.adgoncharov.surfsummerschool2025.state.FilterScreenState
import ru.adgoncharov.triviaapi.models.Category
import ru.adgoncharov.triviaapi.models.Difficulty
import ru.adgoncharov.triviaapi.repository.TriviaRepository

class FilterScreenViewModel : ViewModel() {
    private val repository = TriviaRepository()

    private val _quizWithQuestions = MutableStateFlow<QuizWithQuestions>(QuizWithQuestions())
    val quizWithQuestions: StateFlow<QuizWithQuestions> = _quizWithQuestions.asStateFlow()

    private val _state = MutableStateFlow<FilterScreenState>(FilterScreenState.Idle)
    val state: StateFlow<FilterScreenState> = _state

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _currentCategory = MutableStateFlow<Int>(0)
    val currentCategory: StateFlow<Int> = _currentCategory

    private val _difficulties = MutableStateFlow<List<Difficulty>>(
        listOf(
            Difficulty.ANY,
            Difficulty.EASY,
            Difficulty.MEDIUM,
            Difficulty.HARD
        )
    )
    val difficulties: StateFlow<List<Difficulty>> = _difficulties

    private val _currentDifficulty = MutableStateFlow<Difficulty>(Difficulty.ANY)
    val currentDifficulty: StateFlow<Difficulty> = _currentDifficulty

    fun loadCategories() {
        viewModelScope.launch {
            _state.value = FilterScreenState.Loading
            try {
                val response = repository.getCategories()
                if (response.isSuccessful) {
                    val categories = response.body()?.triviaCategories ?: emptyList()
                    _categories.value =
                        (listOf<Category>(Category(0, "Все категории")) + categories).toList()
                    _currentCategory.value = _categories.value[0].id
                    _currentDifficulty.value = Difficulty.ANY
                    _quizWithQuestions.value = _quizWithQuestions.value.copy(
                        quiz = _quizWithQuestions.value.quiz.copy(
                            category = getCategoryName(
                                _currentCategory.value
                            )
                        )
                    )

                    _quizWithQuestions.value = _quizWithQuestions.value.copy(
                        quiz = _quizWithQuestions.value.quiz.copy(difficulty = _currentDifficulty.value.description)
                    )
                    _state.value = FilterScreenState.Success
                } else {
                    _state.value = FilterScreenState.Error("Ошибка запроса: ${response.code()}")
                }
            } catch (e: Exception) {
                _state.value = FilterScreenState.Error("Ошибка: ${e.message}")
            }
        }
    }

    fun setCategory(id: Int) {
        _currentCategory.value = id
        _quizWithQuestions.value = _quizWithQuestions.value.copy(
            quiz = _quizWithQuestions.value.quiz.copy(
                category = getCategoryName(_currentCategory.value)
            )
        )
    }

    fun setDifficulty(difficulty: Difficulty) {
        _currentDifficulty.value = difficulty
        _quizWithQuestions.value = _quizWithQuestions.value.copy(
            quiz =
                _quizWithQuestions.value.quiz.copy(difficulty = _currentDifficulty.value.description)
        )
    }

    fun getCategoryName(id: Int): String {
        return _categories.value.filter { it.id == id }[0].name
    }

    fun getCategory(): String {
        return getCategoryName(_currentCategory.value)
    }

    fun getDifficulty(): String {
        return _currentDifficulty.value.description
    }

}