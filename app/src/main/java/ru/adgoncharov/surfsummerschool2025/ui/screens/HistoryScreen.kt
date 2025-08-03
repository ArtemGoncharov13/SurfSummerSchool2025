package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.HistoryScreenViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.adgoncharov.surfsummerschool2025.ui.component.DeleteButton
import ru.adgoncharov.surfsummerschool2025.ui.component.EmptyHistoryCard
import ru.adgoncharov.surfsummerschool2025.ui.component.QuizCard
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun HistoryScreen(
    viewModel: HistoryScreenViewModel = viewModel(),
    onClick: () -> Unit = {},
    onClickStart: () -> Unit = {},
    onLongClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val longPressedQuizId = remember { mutableStateOf<Long?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val quizzes by viewModel.quizzes.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadAllQuizzes()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        longPressedQuizId.value = null
                    }
                )
            }
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize().background(Blue),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "История",
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    color = White,
                    textAlign = TextAlign.Center,
                )
            }

            if (quizzes.isNotEmpty()) {
                items(quizzes) { quiz ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        QuizCard(
                            quiz = quiz,
                            onClick = {
                                coroutineScope.launch {
                                    viewModel.loadQuizById(quiz.id)
                                    onClick()
                                }
                            },
                            onLongClick = {
                                longPressedQuizId.value = if (longPressedQuizId.value == quiz.id) null else quiz.id
                            }
                        )
                        if (longPressedQuizId.value == quiz.id) {
                            DeleteButton(
                                onClick = {
                                    viewModel.deleteQuizById(quiz.id)
                                    viewModel.clearSelectedQuiz()
                                    longPressedQuizId.value = null
                                }
                            )
                        }
                    }

                }
            } else {
                item {
                    EmptyHistoryCard(onClick = onClickStart)
                }
            }
        }
    }
}


@Preview
@Composable
fun HistoryScreenPreview() {
    //HistoryScreen()
}