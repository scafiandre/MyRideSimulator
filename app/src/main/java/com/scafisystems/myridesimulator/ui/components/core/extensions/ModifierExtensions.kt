package com.scafisystems.myridesimulator.ui.components.core.extensions

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role

inline fun Modifier.modifyIfTrue(value: Boolean, map: Modifier.() -> Modifier): Modifier =
    if (value) map() else this

inline fun <P> Modifier.modifyIfNotNull(value: P, map: Modifier.(P) -> Modifier): Modifier =
    if (value != null) map(value) else this

@Composable
fun Modifier.animatedClickable(
    defaultColor: Color,
    selectedColor: Color,
    shape: Shape,
    onclick: () -> Unit,
    role: Role = Role.Button,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    onClickLabel: String? = null,
    skipAnimation: Boolean = false

): Modifier = animatedClickable(
    defaultColor,
    selectedColor,
    shape,
    onclick,
    role,
    interactionSource,
    enabled,
    onClickLabel,
    skipAnimation
)