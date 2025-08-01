package ru.adgoncharov.surfsummerschool2025.ui.component

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.LightPurple
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun QuestionCard(
    currentAnswer: Int, allAnswers: Int, question: String,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(46.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Top(currentAnswer, allAnswers, question)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AnswerCard(VariantAnswer.NONE, "Груша", Modifier.fillMaxWidth())
                AnswerCard(VariantAnswer.SELECTED, "Груша1", Modifier.fillMaxWidth())
                AnswerCard(VariantAnswer.WRONG, "Груша2", Modifier.fillMaxWidth())
                AnswerCard(VariantAnswer.CORRECT, "Груша3", Modifier.fillMaxWidth())
                AnswerCard(
                    VariantAnswer.NONE, "Груша3", Modifier
                        .alpha(0f)
                        .fillMaxWidth()
                )
            }


            Button("Далее", modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun Top(
    currentAnswer: Int,
    allAnswers: Int,
    question: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        ProgressBar(currentAnswer, allAnswers)
        QuestionText(question)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TopPreview() {
    Top(1, 5, "Как переводится слово \"apple\"?")
}

@Composable
fun ProgressBar(
    currentAnswer: Int,
    allAnswers: Int,
    fontColor: Color = LightPurple,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Вопрос $currentAnswer из $allAnswers",
        fontSize = 16.sp,
        fontFamily = interFontFamily,
        color = fontColor,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun QuestionText(question: String) {
    Text(
        text = question,
        fontSize = 18.sp,
        fontFamily = interFontFamily,
        color = Black,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )
}

@Preview
@Composable
fun QuestionCardPreview() {
    QuestionCard(
        1, 5, "Как переводится слово \"apple\"?"
    )
}