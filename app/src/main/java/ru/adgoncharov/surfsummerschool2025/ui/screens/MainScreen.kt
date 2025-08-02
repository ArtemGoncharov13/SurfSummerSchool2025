package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import ru.adgoncharov.surfsummerschool2025.state.StartScreenState
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizResultScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.StartScreenViewModel
import ru.adgoncharov.triviaapi.models.Question

enum class Screen {
    Start,
    Quiz,
    Result,
    Loading
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val startViewModel: StartScreenViewModel = viewModel()
    val state by startViewModel.state.collectAsState()

    val quizViewModel: QuizScreenViewModel = viewModel()
    val quizResultViewModel: QuizResultScreenViewModel = viewModel()

    var screen by remember { mutableStateOf(Screen.Start) }
    var quizQuestions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var correctCount by remember { mutableStateOf(0) }

    when (screen) {
        Screen.Start -> StartScreen(
            modifier = modifier,
            onStart = {
                startViewModel.loadQuestions()
                screen = Screen.Loading
            },
        )

        Screen.Loading -> {
            when (state) {
                is StartScreenState.Loading -> LoadingScreen(modifier)
                is StartScreenState.Success -> {
                    quizQuestions = (state as StartScreenState.Success).questions
                    quizViewModel.startQuiz(quizQuestions)
                    screen = Screen.Quiz
                }

                is StartScreenState.Error -> {
                    // Здесь можно показать ошибку или экран с кнопкой "Попробовать снова"
                }

                else -> Unit
            }
        }

        Screen.Quiz -> if (quizQuestions.isNotEmpty()) QuizScreen(
            modifier = modifier,
            questions = quizQuestions,
            onFinish = {
                correctCount = it
                quizResultViewModel.getResults(
                    quizQuestions,
                    quizViewModel.getAllShuffledAnswers(),
                    quizViewModel.getAnswers()
                )
                screen = Screen.Result
            }
        )

        Screen.Result -> QuizResultScreen(
            quizQuestions,
            quizViewModel.getAnswers(),
            quizViewModel.getAllShuffledAnswers(),
            viewModel = quizResultViewModel,
            onRestart = {
                quizQuestions = emptyList()
                correctCount = 0
                quizViewModel.resetQuiz()
                screen = Screen.Start
            }
        )
    }
}
