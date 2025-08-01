package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import ru.adgoncharov.surfsummerschool2025.R
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.White

@Composable
fun Logo(modifier: Modifier = Modifier, color: Color = White) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
        contentDescription = "Logo",
        modifier = modifier,
        tint = color,
    )
}

@Preview
@Composable
fun LogoPreview() {
    Column {
        Logo()
        Logo(color = Black)
    }
}