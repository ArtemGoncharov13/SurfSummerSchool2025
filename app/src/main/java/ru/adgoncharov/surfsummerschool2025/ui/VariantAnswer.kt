package ru.adgoncharov.surfsummerschool2025.ui

import androidx.compose.ui.graphics.Color
import ru.adgoncharov.surfsummerschool2025.ui.theme.Black
import ru.adgoncharov.surfsummerschool2025.ui.theme.DarkIndigo
import ru.adgoncharov.surfsummerschool2025.ui.theme.DirtyWhite
import ru.adgoncharov.surfsummerschool2025.ui.theme.Green
import ru.adgoncharov.surfsummerschool2025.ui.theme.Red

enum class VariantAnswer(val borderColor: Color, val fontColor: Color) {
    NONE(DirtyWhite, Black),
    SELECTED(DarkIndigo, DarkIndigo),
    WRONG(Red, Red),
    CORRECT(Green, Green)
}