package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adgoncharov.surfsummerschool2025.ui.component.Logo
import ru.adgoncharov.surfsummerschool2025.ui.component.QuestionCard
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.triviaapi.models.Question

@Composable
fun QuizScreen(
    questions: List<Question>,
    onFinish: (correctAnswer: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var correctCount by remember { mutableStateOf(0) }

    val currentQuestion = questions.getOrNull(currentIndex)
    if (currentQuestion == null) {
        onFinish(correctCount)
        return
    }

    val shuffledAnswers = remember(currentQuestion) {
        (currentQuestion.incorrectAnswers + listOfNotNull(currentQuestion.correctAnswer)).shuffled()
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
            QuestionCard(
                currentAnswer = currentIndex + 1,
                allAnswers = questions.size,
                question = currentQuestion.question,
                answers = shuffledAnswers,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = {selectedAnswer = it},
                onNextClicked = {
                    if (selectedAnswer == currentQuestion.correctAnswer) {
                        correctCount++
                    }
                    selectedAnswer = null
                    currentIndex++
                }
            )
        }
    }
}

@Preview
@Composable
fun QuizScreenPreview() {

}
