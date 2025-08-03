package ru.adgoncharov.surfsummerschool2025.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.database.models.domain.Question
import ru.adgoncharov.surfsummerschool2025.ui.component.Logo
import ru.adgoncharov.surfsummerschool2025.ui.component.QuestionCard
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.QuizScreenViewModel
import ru.adgoncharov.triviaapi.models.QuestionModel

@Composable
fun QuizScreen(
    questions: List<Question>,
    viewModel: QuizScreenViewModel = viewModel(),
    onFinish: (correctAnswer: Int) -> Unit,
    modifier: Modifier = Modifier
) {
//    LaunchedEffect(true) {
//        if (questions.isNotEmpty()) {
//            viewModel.startQuiz(questions)
//        }
//    }
    Log.d("QuizScreen", "size questions: ${viewModel.totalQuestions()}")
    val quizFinished by viewModel.quizFinished.collectAsState()

    if (quizFinished) {
        onFinish(viewModel.getCorrectAnswersCount())
        return
    }

    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedAnswer by viewModel.selectedAnswer.collectAsState()


    val currentQuestion = viewModel.getCurrentQuestion()
    if (currentQuestion == null) {
        onFinish(viewModel.getCorrectAnswersCount())
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Blue)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Logo()
            Log.d(
                "QuizScreen",
                "Questions: ${questions[currentIndex].question}  Answer: ${questions[currentIndex].correctAnswer}"
            )
            QuestionCard(
                currentAnswer = currentIndex + 1,
                viewModel = viewModel,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = {
                    viewModel.selectAnswer(it)
                },
                onNextClicked = {
                    viewModel.provideAnswer()
                }
            )
        }

        Text(
            text = "Вернуться к предыдущим вопросам нельзя",
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            color = White,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
fun QuizScreenPreview() {

}
