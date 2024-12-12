package com.scafisystems.myridesimulator.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiTokenSize
import com.scafisystems.myridesimulator.ui.components.spinner.ScafiSpinner

@Composable
fun ScafiLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .alpha(0.5f)
            .background(Color.White)
    ) {
        ScafiSpinner(
            modifier = Modifier.fillMaxSize(),
            size = ScafiTokenSize.Small
        )
    }
}

@Preview(name = "ScafiLoading")
@Composable
private fun ScafiLoadingPreview() {
    ScafiLoading()
}