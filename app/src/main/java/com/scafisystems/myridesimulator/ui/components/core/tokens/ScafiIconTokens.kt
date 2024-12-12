package com.scafisystems.myridesimulator.ui.components.core.tokens

import androidx.compose.ui.graphics.Color

object ScafiIconColors {
    val default: Color = Color(0x97000E18)
    val negative: Color = Color(0xD4C30007)
}

enum class ScafiIconSize(
    val value: Float = 18F
){
    Xlarge,
    Large,
    Medium,
    Small
}