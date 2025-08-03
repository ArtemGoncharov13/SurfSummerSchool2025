package ru.adgoncharov.surfsummerschool2025.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adgoncharov.surfsummerschool2025.R
import ru.adgoncharov.surfsummerschool2025.state.StartScreenState
import ru.adgoncharov.surfsummerschool2025.ui.component.Logo
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Blue)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(120.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Logo()
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_loader),
                contentDescription = "loader",
                tint = Color.Unspecified,
            )
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}
