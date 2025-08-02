package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ru.adgoncharov.triviaapi.models.Question

enum class Screen {
    Start,
    Quiz,
    Result
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var screen by remember { mutableStateOf(Screen.Start) }
    var quizQuestions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var correctCount by remember { mutableStateOf(0) }

    when (screen) {
        Screen.Start -> StartScreen(
            modifier = modifier,
            onSuccess = {
                quizQuestions = it
                screen = Screen.Quiz
            }
        )
        Screen.Quiz -> QuizScreen(
            modifier = modifier,
            questions = quizQuestions,
            onFinish = {
                correctCount = it
                screen = Screen.Result
            }
        )
        Screen.Result -> QuizResultScreen(
            correctCount = correctCount,
            totalQuestions = quizQuestions.size,
            onRestart = {
                quizQuestions = emptyList()
                correctCount = 0
                screen = Screen.Start
            }
        )
    }
}
