package ru.adgoncharov.surfsummerschool2025.ui.component

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.database.models.domain.Quiz
import ru.adgoncharov.surfsummerschool2025.ui.theme.DarkIndigo
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import java.text.SimpleDateFormat
import java.util.Locale
@Composable
fun QuizCard(
    quiz: Quiz,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(40.dp),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { onClick() },
                        onLongPress = { onLongClick() }
                    )
                }
                .background(White)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            InfoLine(quiz)
            DateLine(quiz)
            CategoryAndDifficultyLine(quiz)
        }
    }
}

@Composable
fun InfoLine(quiz: Quiz) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = "Quiz ${quiz.id}" ,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = DarkIndigo,
        )
        StarLine(
            sizeStar = 16,
            countAllStar = 5,
            countFillStar = quiz.correctAnswer,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun DateLine(quiz: Quiz) {
    val dateFormat: SimpleDateFormat = remember {
        SimpleDateFormat("d MMMM", Locale("ru"))
    }
    val timeFormat: SimpleDateFormat = remember {
        SimpleDateFormat("HH:mm", Locale("ru"))
    }

    val dateText = dateFormat.format(quiz.date)
    val timeText = timeFormat.format(quiz.date)
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = dateText,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )

        Text(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = timeText,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun CategoryAndDifficultyLine(quiz: Quiz) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Категория: ${quiz.category}",
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )

        Text(
            text = "Сложность: ${quiz.difficulty}",
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
fun QuizCardPreview() {
    //QuizCard()
}