package ru.adgoncharov.surfsummerschool2025.ui.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.surfsummerschool2025.R
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun HistoryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        onClick = onClick,
        colors = getWhiteButtonColor(),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(horizontal =  12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "История",
                color = Blue,
                fontFamily = interFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_history),
                contentDescription = "History",
                tint = Color.Unspecified,
            )

        }
    }
}

@Preview
@Composable
fun HistoryButtonPreview() {
    HistoryButton()
}