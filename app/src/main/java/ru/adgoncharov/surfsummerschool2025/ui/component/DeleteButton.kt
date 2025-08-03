package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import ru.adgoncharov.surfsummerschool2025.ui.theme.Red
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun DeleteButton(onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = getWhiteButtonColor(),
    ) {
        Box(
            modifier = Modifier.width(230.dp).padding(12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "Удалить",
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Red,
            )
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = ImageVector.vectorResource(R.drawable.ic_delete),
                contentDescription = "delete",
                tint = Color.Unspecified,
            )
        }
    }
}

@Preview
@Composable
fun DeleteButtonPreview() {
    DeleteButton()
}