package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.surfsummerschool2025.state.FilterScreenState
import ru.adgoncharov.surfsummerschool2025.state.StartScreenState
import ru.adgoncharov.surfsummerschool2025.viewmodels.FilterScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizResultScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.StartScreenViewModel
import ru.adgoncharov.triviaapi.models.Question

enum class Screen {
    Start,
    Quiz,
    Result,
    LoadingQuestions,
    LoadingCategories,
    Error,
    Filter,
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val startViewModel: StartScreenViewModel = viewModel()
    val stateStartScreen by startViewModel.state.collectAsState()

    val filterScreenViewModel: FilterScreenViewModel = viewModel()
    val stateFilterScreen by filterScreenViewModel.state.collectAsState()

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
                filterScreenViewModel.loadCategories()
                screen = Screen.LoadingQuestions
            },
            onFilter = {
                filterScreenViewModel.loadCategories()
                screen = Screen.LoadingCategories
            }
        )

        Screen.LoadingQuestions -> {
            when (stateStartScreen) {
                is StartScreenState.Loading -> LoadingScreen(modifier)
                is StartScreenState.Success -> {
                    quizQuestions = (stateStartScreen as StartScreenState.Success).questions
                    quizViewModel.startQuiz(quizQuestions)
                    screen = Screen.Quiz
                }

                is StartScreenState.Error -> {
                    screen = Screen.Error
                }

                else -> Unit
            }
        }

        Screen.LoadingCategories -> {
            when (stateFilterScreen) {
                is FilterScreenState.Loading -> LoadingScreen(modifier)
                is FilterScreenState.Success -> {
                    screen = Screen.Filter
                }
                is FilterScreenState.Error -> {
                    screen = Screen.Error
                }

                else -> Unit
            }
        }

        Screen.Filter -> {
            FilterScreen(
                viewModel = filterScreenViewModel,
                onStart = {
                    startViewModel.loadQuestions(
                        categoryId = filterScreenViewModel.currentCategory.value,
                        difficulty = filterScreenViewModel.currentDifficulty.value
                    )
                    screen = Screen.LoadingQuestions
                }
            )
        }

        Screen.Error -> {
            ErrorScreen(
                onStart = {
                    startViewModel.loadQuestions()
                    screen = Screen.LoadingQuestions
                },
                onFilter = {
                    screen = Screen.Filter
                }
            )
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
