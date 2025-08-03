package ru.adgoncharov.surfsummerschool2025.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.adgoncharov.surfsummerschool2025.ui.component.Button
import ru.adgoncharov.surfsummerschool2025.ui.component.CategoryPeek
import ru.adgoncharov.surfsummerschool2025.ui.component.DifficultyPeek
import ru.adgoncharov.surfsummerschool2025.ui.component.HistoryButton
import ru.adgoncharov.surfsummerschool2025.ui.component.Logo
import ru.adgoncharov.surfsummerschool2025.ui.theme.Blue
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.FilterScreenViewModel
import ru.adgoncharov.surfsummerschool2025.viewmodels.StartScreenViewModel
import ru.adgoncharov.triviaapi.models.Difficulty

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    viewModel: FilterScreenViewModel = viewModel(),
    onStart: () -> Unit = {},
) {
    LaunchedEffect(true) {
        viewModel.loadCategories()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Blue)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Logo()
            CategoryAndDifficultyPeek(viewModel, onStartClick = onStart)
        }
    }
}


@Composable
fun CategoryAndDifficultyPeek(viewModel: FilterScreenViewModel, onStartClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(46.dp),
        colors = CardDefaults.cardColors(containerColor = White),
    ) {
        Column(
            modifier = Modifier.padding(top = 32.dp, start = 24.dp, end = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Почти готовы!",
                    fontFamily = interFontFamily,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "Осталось выбрать категорию и сложность викторины.",
                    fontFamily = interFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CategoryPeek(viewModel)
                DifficultyPeek(viewModel)
            }

            Button(
                text = "Начать викторину",
                modifier = Modifier.fillMaxWidth(),
                onClick = onStartClick,
            )
        }
    }
}