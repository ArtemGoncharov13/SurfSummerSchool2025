package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Arrangement
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
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun EmptyHistoryCard(
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(46.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Вы еще не проходили ни одной викторины",
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Button(
                text = "Начать викторину",
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick,
            )
        }
    }
}

@Preview
@Composable
fun EmptyHistoryCardPreview() {
    EmptyHistoryCard()
}