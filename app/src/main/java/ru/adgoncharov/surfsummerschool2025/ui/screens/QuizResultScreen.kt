package ru.adgoncharov.surfsummerschool2025.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.surfsummerschool2025.ui.component.AnswerEvaluation
import ru.adgoncharov.surfsummerschool2025.ui.component.Button
import ru.adgoncharov.surfsummerschool2025.ui.component.ResultCard
import ru.adgoncharov.surfsummerschool2025.ui.component.getWhiteButtonColor
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizResultScreenViewModel

@Composable
fun QuizResultScreen(
    viewModel: QuizResultScreenViewModel = viewModel(),
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    val countQuestions = viewModel.totalQuestions()
    val correctAnswersCount = viewModel.getCorrectAnswersCount()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Blue)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Результаты",
                        modifier = Modifier.padding(top = 32.dp),
                        color = White,
                        fontFamily = interFontFamily,
                        fontWeight = FontWeight.Black,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                    )

                    DescriptionQuiz(viewModel)
                }
            }

            item {
                ResultCard(correctAnswersCount, countQuestions, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), onClick = onRestart)
            }

            item {
                Text(
                    text = "Твои ответы",
                    color = White,
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            }

            items(countQuestions) { index ->
                Log.d("QuizScreen", "index: $index")
                AnswerEvaluation(
                    currentAnswer = index,
                    viewModel = viewModel,
                )
            }

            item {
                Button(
                    "НАчать заново",
                    modifier = Modifier.fillMaxWidth(),
                    colors = getWhiteButtonColor(),
                    onClick = onRestart
                )
            }
            item {
                Spacer(Modifier.padding(top = 90.dp))
            }
        }
    }
}


@Composable
fun DescriptionQuiz(viewModel: QuizResultScreenViewModel) {
    Column (
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Категория: ${viewModel.getCategory()}",
            color = White,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = "Сложность: ${viewModel.getDifficulty()}",
            color = White,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun QuizResultScreenPreview() {
    QuizResultScreen(onRestart = {})
}