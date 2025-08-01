package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.LightGray
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Black,
    enable: Boolean = true,
    colors: ButtonColors = getDefaultButtonColor(),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enable,
        shape = RoundedCornerShape(CornerSize(16.dp)),
        contentPadding = PaddingValues(16.dp),
        colors = colors,
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = interFontFamily,
            fontWeight = fontWeight,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}

fun getDefaultButtonColor(): ButtonColors {
    return ButtonColors(
        containerColor = Blue,
        contentColor = White,
        disabledContainerColor = LightGray,
        disabledContentColor = White,
    )
}

fun getWhiteButtonColor(): ButtonColors {
    return ButtonColors(
        containerColor = White,
        contentColor = Black,
        disabledContainerColor = LightGray,
        disabledContentColor = White,
    )
}

@Preview
@Composable
fun DefaultButtonPreview() {
    Column() {
        Button(text = "ДАЛЕЕ", modifier = Modifier.fillMaxWidth(), onClick = {});
        Button(
            text = "Далее",
            Modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            onClick = {});
        Button(text = "ДАлее", Modifier.fillMaxWidth(), enable = false, onClick = {});
        Button(
            text = "ДАлее",
            Modifier.fillMaxWidth(),
            colors = getWhiteButtonColor(),
            onClick = {}
        );
    }
}