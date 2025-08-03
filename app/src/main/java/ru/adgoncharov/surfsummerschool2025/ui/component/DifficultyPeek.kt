package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.adgoncharov.surfsummerschool2025.ui.theme.DarkIndigo
import ru.adgoncharov.surfsummerschool2025.ui.theme.White
import ru.adgoncharov.surfsummerschool2025.ui.theme.interFontFamily
import ru.adgoncharov.surfsummerschool2025.viewmodels.FilterScreenViewModel
import ru.adgoncharov.triviaapi.models.Difficulty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyPeek(viewModel: FilterScreenViewModel) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    val difficulties by viewModel.difficulties.collectAsState()
    val selectedDifficulty by viewModel.currentDifficulty.collectAsState()

    FilterCard(title = "Сложность", selectedItem = selectedDifficulty.getName()) {
        showSheet = true
    }

    if (showSheet) {
        FilterBottomSheet(
            title = "Сложность",
            sheetState = sheetState,
            onDismiss = { showSheet = false }
        ) {
            difficulties.forEach { difficulty ->
                DifficultyListItem(
                    difficulty = difficulty,
                    isSelected = difficulty == selectedDifficulty,
                    onClick = {
                        viewModel.setDifficulty(difficulty)
                        showSheet = false
                    }
                )
            }
        }
    }
}

@Composable
fun DifficultyListItem(
    difficulty: Difficulty,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(
                text = difficulty.getName(),
                color = if (isSelected) DarkIndigo else Color.Black,
                fontFamily = interFontFamily,
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        },
        modifier = Modifier.clickable { onClick() },
        colors = ListItemDefaults.colors(containerColor = White)
    )
}