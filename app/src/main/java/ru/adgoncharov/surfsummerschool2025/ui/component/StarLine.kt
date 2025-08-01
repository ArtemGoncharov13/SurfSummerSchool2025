package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adgoncharov.surfsummerschool2025.R
import ru.adgoncharov.surfsummerschool2025.ui.theme.SurfSummerSchool2025Theme
import ru.adgoncharov.surfsummerschool2025.ui.theme.Yellow

@Composable
fun StarLine(
    countFillStar: Int,
    countAllStar: Int = 5,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(countFillStar) {
            CorrectStar()
        }
        repeat(countAllStar - countFillStar) {
            IncorrectStar()
        }

    }
}

@Composable
fun CorrectStar() {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_fill_star),
        contentDescription = "Correct Star",
        tint = Color.Unspecified,
    )
}

@Composable
fun IncorrectStar() {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_unfill_star),
        contentDescription = "Fill Star",
        tint = Color.Unspecified,
    )
}

@Preview(
   showBackground = true
)
@Composable
fun StarLinePreview() {
    SurfSummerSchool2025Theme {
        StarLine(3)
    }
}