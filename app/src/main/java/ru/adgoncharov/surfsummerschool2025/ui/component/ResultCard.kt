package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.Yellow
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun ResultCard(
    correctAnswer: Int,
    allAnswer: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(46.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            ResultInStar(correctAnswer, allAnswer)
            ComplimentText(correctAnswer, allAnswer)
        }

        Button(
            "Начать заново", modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            onClick = onClick,
        )
    }
}

@Composable
fun ResultInStar(
    correctAnswer: Int,
    allAnswer: Int,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        StarLine(correctAnswer, allAnswer)
        AnswerResult(correctAnswer, allAnswer)
    }
}

@Composable
fun AnswerResult(
    correctAnswer: Int,
    allAnswer: Int = 5,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "$correctAnswer из $allAnswer",
        fontSize = 16.sp,
        fontFamily = interFontFamily,
        color = Yellow,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun ComplimentText(correctAnswer: Int, allAnswer: Int = 5) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Почти идеально",
            fontSize = 24.sp,
            fontFamily = interFontFamily,
            color = Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "4/5 — очень близко к совершенству. Ещё один шаг!",
            fontSize = 16.sp,
            fontFamily = interFontFamily,
            color = Black,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun ResultInStarPreview() {
    ResultInStar(4, 5)
}

@Preview
@Composable
fun ResultCardPreview() {
    ResultCard(4, 5, onClick =  {})
}