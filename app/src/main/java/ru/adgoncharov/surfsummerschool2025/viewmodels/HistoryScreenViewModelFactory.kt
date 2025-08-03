package ru.adgoncharov.surfsummerschool2025.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.adgoncharov.database.repository.QuizRepository

class HistoryScreenViewModelFactory(
    private val repository: QuizRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}