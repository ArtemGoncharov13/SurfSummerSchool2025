package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.LightGray
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizResultScreenViewModel
import ru.adgoncharov.triviaapi.models.QuestionModel

@Composable
fun AnswerEvaluation(
    currentAnswer: Int,
    viewModel: QuizResultScreenViewModel = viewModel()
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
            TopEvaluation(
                currentAnswer,
                viewModel.totalQuestions(),
                viewModel.getQuestion(currentAnswer).question,
                checkAnswer(viewModel.getAnswer(currentAnswer), viewModel.getQuestion(currentAnswer).correctAnswer)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                for (answerVariant in viewModel.getShuffledAnswers(currentAnswer)) {
                    AnswerCard(
                        answer = getAnswerStatusForAnswerCard(
                            viewModel.getQuestion(currentAnswer).correctAnswer,
                            viewModel.getAnswer(currentAnswer),
                            answerVariant
                        ),
                        answerDescription = answerVariant,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

fun getAnswerStatusForAnswerCard(correctAnswer: String, answer: String?, currentAnswer: String): VariantAnswer {
    return if ((correctAnswer == currentAnswer) && correctAnswer == answer)
        VariantAnswer.CORRECT
    else if (currentAnswer == answer)
        VariantAnswer.WRONG
    else if (currentAnswer == correctAnswer)
        VariantAnswer.CORRECT
    else VariantAnswer.NONE

}

fun checkAnswer(answer: String?, correctAnswer: String): VariantAnswer {
    return if (answer.equals(correctAnswer))
        VariantAnswer.CORRECT
    else
        VariantAnswer.WRONG
}

@Composable
fun TopEvaluation(
    currentAnswerIndex: Int,
    totalQuestions: Int,
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
                currentAnswerIndex + 1,
                totalQuestions = totalQuestions,
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

@Composable
fun QuestionText(question: QuestionModel) {
    Text(
        text = question.question,
        fontSize = 18.sp,
        fontFamily = interFontFamily,
        color = Black,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )
}