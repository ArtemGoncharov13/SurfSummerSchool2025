package ru.adgoncharov.surfsummerschool2025.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.database.QuizDatabase
import ru.adgoncharov.database.models.domain.Question
import ru.adgoncharov.database.models.domain.QuizWithQuestions
import ru.adgoncharov.database.repository.QuizRepository
import ru.adgoncharov.database.repository.QuizRepositoryImpl
import ru.adgoncharov.surfsummerschool2025.state.FilterScreenState
import ru.adgoncharov.surfsummerschool2025.state.HistoryScreenState
import ru.adgoncharov.surfsummerschool2025.state.StartScreenState
import ru.adgoncharov.surfsummerschool2025.viewmodels.FilterScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.HistoryScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.HistoryScreenViewModelFactory
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizResultScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.StartScreenViewModel
import ru.adgoncharov.triviaapi.models.Difficulty
import ru.adgoncharov.triviaapi.models.QuestionModel

enum class Screen {
    Start,
    QuizIsOn,
    Result,
    LoadingQuestions,
    LoadingCategories,
    LoadingHistory,
    Error,
    Filter,
    HistoryScreen,
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val database = QuizDatabase.getInstance(LocalContext.current)
    val quizDao = database.quizDao()
    val quizRepository = QuizRepositoryImpl(quizDao)
    val factory = HistoryScreenViewModelFactory(quizRepository)

    val historyScreenViewModel: HistoryScreenViewModel = viewModel(factory = factory)
    val historyScreenState by historyScreenViewModel.state.collectAsState()

    val startViewModel: StartScreenViewModel = viewModel()
    val stateStartScreen by startViewModel.state.collectAsState()

    val filterScreenViewModel: FilterScreenViewModel = viewModel()
    val stateFilterScreen by filterScreenViewModel.state.collectAsState()

    val quizViewModel: QuizScreenViewModel = viewModel()
    val quizResultViewModel: QuizResultScreenViewModel = viewModel()

    var screen by remember { mutableStateOf(Screen.Start) }
    var quizQuestions by remember { mutableStateOf<List<Question>>(emptyList()) }

    when (screen) {
        Screen.Start -> StartScreen(
            modifier = modifier,
            onStart = {
                startViewModel.loadQuestions(
                    amount = 5,
                    filterScreenViewModel.currentCategory.value,
                    filterScreenViewModel.currentDifficulty.value
                )
                screen = Screen.LoadingQuestions
            },
            onFilter = {
                filterScreenViewModel.loadCategories()
                screen = Screen.LoadingCategories
            },
            onHistory = {
                screen = Screen.HistoryScreen
            }
        )

        Screen.HistoryScreen -> {
            HistoryScreen(
                modifier = modifier,
                viewModel = historyScreenViewModel,
                onClick = {
                    quizResultViewModel.getResults(
                        historyScreenViewModel.selectedQuiz.value ?: QuizWithQuestions()
                    )
                    screen = Screen.Result
                    historyScreenViewModel.clearSelectedQuiz()
                },
                onClickStart = {
                    screen = Screen.Start
                }
            )
        }

        Screen.LoadingQuestions -> {
            when (stateStartScreen) {
                is StartScreenState.Loading -> LoadingScreen(modifier)
                is StartScreenState.Success -> {
                    quizQuestions = (stateStartScreen as StartScreenState.Success).questions
                    try {
                        quizViewModel.startQuiz(quizQuestions, filterScreenViewModel.getCategory(), filterScreenViewModel.getDifficulty())
                        filterScreenViewModel.setCategory(0)
                        filterScreenViewModel.setDifficulty(Difficulty.ANY)
                    } catch (e: Exception) {
                        quizViewModel.startQuiz(quizQuestions)
                    }

                    screen = Screen.QuizIsOn
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

        Screen.LoadingHistory -> {
            when (historyScreenState) {
                is HistoryScreenState.Loading -> LoadingScreen(modifier)
                is HistoryScreenState.Success -> {
                    screen = Screen.HistoryScreen
                }

                is HistoryScreenState.Error -> {
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
                    Log.d("MainScreen", "categoryId = ${filterScreenViewModel.currentCategory.value}")
                    Log.d("MainScreen", "difficulty = ${filterScreenViewModel.currentDifficulty.value}")
                    Log.d("MainScreen", "//categoryId = ${startViewModel}")
                    Log.d("MainScreen", "//difficulty = ${filterScreenViewModel.currentDifficulty.value}")
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

        Screen.QuizIsOn -> if (quizQuestions.isNotEmpty()) {
            Log.d("QuizScreen", "size questions from MainScreen: ${quizQuestions.size}")
            QuizScreen(
                modifier = modifier,
                questions = quizQuestions,
                viewModel = quizViewModel,
                onFinish = {
                    val quizWithNewId = quizViewModel.quizWithQuestions.value.copy(
                        quiz = quizViewModel.quizWithQuestions.value.quiz.copy(id = 0),
                        questions = quizViewModel.quizWithQuestions.value.questions.map { it.copy(id = 0, quizId = 0) }
                    )
                    historyScreenViewModel.insertQuizWithQuestions(quizWithNewId)
                    quizResultViewModel.getResults(quizWithNewId)
                    screen = Screen.Result
                }
            )
        }

        Screen.Result -> QuizResultScreen(
            viewModel = quizResultViewModel,
            onRestart = {
                quizQuestions = emptyList()
                quizViewModel.resetQuiz()
                screen = Screen.Start
            }
        )

    }
}
