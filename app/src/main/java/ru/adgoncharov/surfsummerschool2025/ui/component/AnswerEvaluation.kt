package ru.adgoncharov.surfsummerschool2025.ui.component

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import ru.adgoncharov.surfsummerschool2025.ui.theme.LightGray
import ru.adgoncharov.surfsummerschool2025.ui.theme.LightPurple
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun AnswerEvaluation(
    currentAnswer: Int, allAnswers: Int, question: String, answer: VariantAnswer
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
            TopEvaluation(currentAnswer, allAnswers, question, answer)

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
            }
        }
    }
}

@Composable
fun TopEvaluation(
    currentAnswer: Int,
    allAnswers: Int,
    question: String,
    answer: VariantAnswer,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            ProgressBar(
                currentAnswer,
                allAnswers,
                fontColor = LightGray,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            RadioButton(
                answer = answer,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        QuestionText(question)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TopPreview2() {
    Top(1, 5, "Как переводится слово \"apple\"?")
}

@Preview
@Composable
fun AnswerEvaluationPreview() {
    AnswerEvaluation(
        1, 5, "Как переводится слово \"apple\"?", VariantAnswer.NONE
    )
}