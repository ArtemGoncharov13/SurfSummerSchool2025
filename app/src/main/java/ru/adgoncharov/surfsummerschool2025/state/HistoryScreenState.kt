package ru.adgoncharov.surfsummerschool2025.state

sealed class HistoryScreenState {
    object Idle : HistoryScreenState()
    object Loading : HistoryScreenState()
    object Success : HistoryScreenState()
    data class Error(val message: String) : HistoryScreenState()
}