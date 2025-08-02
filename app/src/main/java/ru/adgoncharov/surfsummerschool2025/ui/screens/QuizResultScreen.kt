package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer
import ru.adgoncharov.surfsummerschool2025.ui.component.AnswerEvaluation
import ru.adgoncharov.surfsummerschool2025.ui.component.Button
import ru.adgoncharov.surfsummerschool2025.ui.component.Logo
import ru.adgoncharov.surfsummerschool2025.ui.component.QuestionCard
import ru.adgoncharov.surfsummerschool2025.ui.component.ResultCard
import ru.adgoncharov.surfsummerschool2025.ui.component.getWhiteButtonColor
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun QuizResultScreen(
    correctCount: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                Text(
                    text = "Результаты",
                    modifier = Modifier.padding(top = 32.dp),
                    color = White,
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                )
            }

            item {
                ResultCard(correctCount, totalQuestions, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp), onClick = onRestart)
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

            items(totalQuestions) { index ->
                AnswerEvaluation(
                    currentAnswer = index + 1,
                    allAnswers = totalQuestions,
                    question = "Вопрос ${index + 1}",
                    answer = VariantAnswer.CORRECT
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

@Preview
@Composable
fun QuizResultScreenPreview() {

}