package com.scafisystems.myridesimulator.ui.components.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScafiVerticalSpacer(
    height: Dp = 8.dp,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(height))
}

@Preview(name = "VerticalSpacer")
@Composable
private fun PreviewVerticalSpacer() {
    ScafiVerticalSpacer()
}