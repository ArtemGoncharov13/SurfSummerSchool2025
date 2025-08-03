package ru.adgoncharov.surfsummerschool2025.ui.screens

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ru.adgoncharov.surfsummerschool2025.state.StartScreenState
import ru.adgoncharov.surfsummerschool2025.ui.component.Button
import ru.adgoncharov.surfsummerschool2025.ui.component.HistoryButton
import ru.adgoncharov.surfsummerschool2025.ui.component.Logo
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.StartScreenViewModel
import ru.adgoncharov.triviaapi.models.Question

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    viewModel: StartScreenViewModel = viewModel(),
    onStart: () -> Unit = {},
    onFilter: () -> Unit = {},
) {


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Blue)
    ) {
        HistoryButton(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 16.dp, top = 16.dp)
                .statusBarsPadding(),
            onClick = {// тут мне нужно получать все Quiz из БД
            }
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Logo()
            WelcomeGroup(onStartClick = onStart, onFilter = onFilter)
        }
    }
}


@Composable
fun WelcomeGroup(onStartClick: () -> Unit = {}, onFilter: () -> Unit = {}) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(46.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Column(
            modifier = Modifier.padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Добро пожаловать в DailyQuiz",
                fontFamily = interFontFamily,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Button(
                text = "Начать викторину",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showDialog = true
                },
            )
        }
    }

    if (showDialog) {
        FilterSettingsDialog(
            onStart = onStartClick,
            onFilter = onFilter,
            onDismiss = { showDialog = false },
        )
    }
}

@Composable
fun FilterSettingsDialog(
    onStart: () -> Unit = {},
    onFilter: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismiss) {
        FilterSettingsContent(
            onStart = onStart,
            onFilter = onFilter
        )
    }
}

@Preview
@Composable
fun FilterSettingsDialogPreview() {
    FilterSettingsDialog()
}

@Composable
fun FilterSettingsContent(onStart: () -> Unit = {}, onFilter: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(46.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Column(
            modifier = Modifier.padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Хотите настроить фильтры для вопросов?",
                fontFamily = interFontFamily,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Button(
                text = "Настроить",
                modifier = Modifier.fillMaxWidth(),
                onClick = onFilter,
            )

            Button(
                text = "Пропустить",
                modifier = Modifier.fillMaxWidth(),
                onClick = onStart,
            )
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen()
}