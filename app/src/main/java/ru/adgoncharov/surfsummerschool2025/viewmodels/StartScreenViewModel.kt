package ru.adgoncharov.surfsummerschool2025.viewmodels

import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.adgoncharov.surfsummerschool2025.state.StartScreenState
import ru.adgoncharov.triviaapi.models.Difficulty
import ru.adgoncharov.triviaapi.repository.TriviaRepository


class StartScreenViewModel : ViewModel() {
    private val repository = TriviaRepository()

    private val _state = MutableStateFlow<StartScreenState>(StartScreenState.Idle)
    val state: StateFlow<StartScreenState> = _state


    fun loadQuestions(
        amount: Int = 5,
        categoryId: Int = 0,
        difficulty: Difficulty = Difficulty.ANY
    ) {
        viewModelScope.launch {
            _state.value = StartScreenState.Loading
            try {
                val response = repository.getQuestions(amount, categoryId, difficulty)
                if (response.isSuccessful) {
                    val body = response.body()
                    val decodedQuestions = body?.results?.map { question ->
                        question.copy(
                            question = decodeHtmlEntities(question.question),
                            correctAnswer = decodeHtmlEntities(question.correctAnswer ?: ""),
                            incorrectAnswers = question.incorrectAnswers?.map {
                                decodeHtmlEntities(
                                    it
                                )
                            } ?: emptyList()
                        )
                    } ?: emptyList()

                    _state.value = StartScreenState.Success(decodedQuestions)
                } else {
                    _state.value = StartScreenState.Error("Ошибка запроса: ${response.code()}")
                }
            } catch (e: Exception) {
                _state.value = StartScreenState.Error("Ошибка: ${e.message}")
            }
        }
    }

    private fun decodeHtmlEntities(text: String): String {
        return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}