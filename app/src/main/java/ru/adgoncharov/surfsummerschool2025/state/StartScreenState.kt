package ru.adgoncharov.surfsummerschool2025.state

import android.os.Message
import ru.adgoncharov.triviaapi.models.Question

sealed class StartScreenState {
    object Idle : StartScreenState()
    object Loading : StartScreenState()
    data class Success(val questions: List<Question>) : StartScreenState()
    data class Error(val message: String) : StartScreenState()
}