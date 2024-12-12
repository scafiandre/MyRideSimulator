package com.scafisystems.myridesimulator.ui.components.core.tokens

import androidx.compose.ui.graphics.Color
import com.scafisystems.myridesimulator.ui.theme.Pink40
import com.scafisystems.myridesimulator.ui.theme.Purple40
import com.scafisystems.myridesimulator.ui.theme.PurpleGrey40

enum class ScafiColorToken(
    var primary: Color = Purple40,
    var secondary: Color = PurpleGrey40,
    var tertiary: Color = Pink40,

    val textBaseColor: Color = Color.Black,

    ) {
    DefaultColor
}