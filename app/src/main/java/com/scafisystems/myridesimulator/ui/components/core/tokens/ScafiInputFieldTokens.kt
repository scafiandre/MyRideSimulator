package com.scafisystems.myridesimulator.ui.components.core.tokens

import androidx.compose.ui.text.TextStyle

enum class ScafiInputFieldSize(
    var size: Float = 16f,
    var inputWrapperHeight: Float = 40f,
    var inputTextVariant: TextStyle = TextStyle()
) {
    Large(
        size = 20f,
        inputWrapperHeight = 48f
    ),
    Medium,
    Small(
        size = 12f,
        inputWrapperHeight = 36f
    )
}
