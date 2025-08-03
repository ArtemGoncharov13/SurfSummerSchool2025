package ru.adgoncharov.surfsummerschool2025.ui.component

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.LightPurple
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizResultScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizScreenViewModel

@Composable
fun QuestionCard(
    currentAnswer: Int,
    viewModel: QuizScreenViewModel,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit,
    onNextClicked: () -> Unit,
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
            Top(currentAnswer, viewModel)

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                viewModel.getAllShuffledAnswers()[currentAnswer - 1].forEach { answer ->
                    var variant = when {
                        selectedAnswer == null -> VariantAnswer.NONE
                        selectedAnswer == answer -> VariantAnswer.SELECTED
                        else -> VariantAnswer.NONE
                    }

                    AnswerCard(
                        answer = variant,
                        answerDescription = answer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        onAnswerSelected(answer)
                    }
                }

                repeat((0).coerceAtLeast(0)) {
                    AnswerCard(
                        answer = VariantAnswer.NONE,
                        answerDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                text = "Далее",
                modifier = Modifier.fillMaxWidth(),
                onClick = onNextClicked,
                enable = selectedAnswer != null
            )
        }
    }
}

@Composable
fun Top(
    currentAnswer: Int,
    viewModel: QuizScreenViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        ProgressBar(currentAnswer = currentAnswer, totalQuestions = viewModel.totalQuestions())
        QuestionText(viewModel.getQuestion(currentAnswer - 1).question)
    }
}

@Composable
fun Top(
    currentAnswer: Int,
    viewModel: QuizResultScreenViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        ProgressBar(currentAnswer = currentAnswer, totalQuestions = viewModel.totalQuestions())
        QuestionText(viewModel.getQuestion(currentAnswer).question)
    }
}

@Preview(
    showBackground = true
)
@Composable
fun TopPreview() {
    //Top(1, 5, "Как переводится слово \"apple\"?")
}

@Composable
fun ProgressBar(
    currentAnswer: Int,
    totalQuestions: Int,
    fontColor: Color = LightPurple,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "Вопрос $currentAnswer из $totalQuestions",
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

}