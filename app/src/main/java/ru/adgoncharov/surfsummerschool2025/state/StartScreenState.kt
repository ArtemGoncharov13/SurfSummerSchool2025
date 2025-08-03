package ru.adgoncharov.surfsummerschool2025.state

import ru.adgoncharov.database.models.domain.Question

sealed class StartScreenState {
    object Idle : StartScreenState()
    object Loading : StartScreenState()
    data class Success(val questions: List<Question>) : StartScreenState()
    data class Error(val message: String) : StartScreenState()
}