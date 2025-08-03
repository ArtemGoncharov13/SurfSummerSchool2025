package ru.adgoncharov.surfsummerschool2025.state

import ru.adgoncharov.triviaapi.models.Category

sealed class FilterScreenState {
    object Idle : FilterScreenState()
    object Loading : FilterScreenState()
    object Success : FilterScreenState()
    data class Error(val message: String) : FilterScreenState()
}