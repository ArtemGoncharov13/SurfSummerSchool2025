package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import java.nio.file.WatchEvent

@Composable
fun AnswerCard(
    answer: VariantAnswer = VariantAnswer.NONE,
    answerDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(CornerSize(16.dp)),
        border = if (answer != VariantAnswer.NONE) BorderStroke(1.dp, answer.borderColor) else null,
        colors = getColorCard(answer),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                answer = answer,
                onClick = onClick,
            )

            Text(
                text = answerDescription,
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                color = answer.fontColor,
            )
        }
    }
}


@Composable
fun getColorCard(answerType: VariantAnswer): CardColors {
    return if (answerType != VariantAnswer.NONE)
        CardDefaults.cardColors(containerColor = Color.Transparent) else CardDefaults.cardColors()
}

@Preview(
    showBackground = true,
)
@Composable
fun AnswerCardPreview() {
    Column {
        AnswerCard(VariantAnswer.NONE, "Answer1", Modifier.fillMaxWidth())
        AnswerCard(VariantAnswer.SELECTED, "Answer2", Modifier.fillMaxWidth())
        AnswerCard(VariantAnswer.WRONG, "Answer3", Modifier.fillMaxWidth())
        AnswerCard(VariantAnswer.CORRECT, "Answer4", Modifier.fillMaxWidth())
    }
}

