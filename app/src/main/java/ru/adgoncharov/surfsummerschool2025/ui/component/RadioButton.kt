package ru.adgoncharov.surfsummerschool2025.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import ru.adgoncharov.surfsummerschool2025.R
import ru.adgoncharov.surfsummerschool2025.ui.VariantAnswer

@Composable
fun RadioButton(
    answer: VariantAnswer = VariantAnswer.NONE,
    onClick: () -> Unit = {},
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    IconToggleButton(
        checked = answer != VariantAnswer.NONE,
        onCheckedChange = { onClick() },
        modifier = modifier
    ) {
        Icon(
            imageVector = getIconRadioButton(answer),
            contentDescription = contentDescription,
            tint = Color.Unspecified
        )
    }
}

@Composable
fun getIconRadioButton(variantAnswer: VariantAnswer): ImageVector {
    return when (variantAnswer) {
        VariantAnswer.NONE -> ImageVector.vectorResource(R.drawable.ic_radio_button_default)
        VariantAnswer.WRONG -> ImageVector.vectorResource(R.drawable.ic_radio_button_wrong)
        VariantAnswer.CORRECT -> ImageVector.vectorResource(R.drawable.ic_radio_button_right)
        VariantAnswer.SELECTED -> ImageVector.vectorResource(R.drawable.ic_radio_button_selected)
    }
}

@Preview
@Composable
fun CustomRadioButtonPreview() {
    Column {
        RadioButton(
            answer = VariantAnswer.WRONG
        )

        RadioButton(
            answer = VariantAnswer.CORRECT
        )

        RadioButton(
            answer = VariantAnswer.SELECTED
        )

        RadioButton(
            answer = VariantAnswer.NONE
        )
    }

}