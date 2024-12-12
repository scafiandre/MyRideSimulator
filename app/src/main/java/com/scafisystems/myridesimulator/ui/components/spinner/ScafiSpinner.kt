package com.scafisystems.myridesimulator.ui.components.spinner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiSpinnerTestId
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiSpinnerTokens
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiSpinnerTokens.spinnerFooterPadding
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiSpinnerTokens.spinnerHeaderPadding
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiTokenSize
import com.scafisystems.myridesimulator.ui.theme.MyRideSimulatorTheme

@Composable
fun ScafiSpinner(
    modifier: Modifier = Modifier,
    header: String? = null,
    footer: String? = null,
    color: Color = ScafiSpinnerTokens.spinnerColor,
    size: ScafiTokenSize = ScafiTokenSize.Medium
) {
    val stokeWidth = remember(size) {
        when (size) {
            ScafiTokenSize.Xlarge -> ScafiSpinnerTokens.stokeWidthXlarge
            ScafiTokenSize.Large -> ScafiSpinnerTokens.stokeWidthLarge
            ScafiTokenSize.Medium -> ScafiSpinnerTokens.stokeWidthMedium
            ScafiTokenSize.Small -> ScafiSpinnerTokens.stokeWidthSmall
        }.dp
    }

    val spinnerSize = remember(size) {
        when (size) {
            ScafiTokenSize.Xlarge -> ScafiSpinnerTokens.spinnerSizeXlarge
            ScafiTokenSize.Large -> ScafiSpinnerTokens.spinnerSizeLarge
            ScafiTokenSize.Medium -> ScafiSpinnerTokens.spinnerSizeMedium
            ScafiTokenSize.Small -> ScafiSpinnerTokens.spinnerSizeSmall
        }.dp
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (header != null) {
            Text(text = header, modifier.padding(bottom = spinnerHeaderPadding.dp))
        }
        CircularProgressIndicator(
            color = color,
            strokeWidth = stokeWidth,
            modifier = Modifier
                .size(spinnerSize)
                .testTag(ScafiSpinnerTestId.SPINNER),
        )
        if (footer != null) {
            Text(text = footer, modifier.padding(top = spinnerFooterPadding.dp))
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ScafiDivider() {
    MyRideSimulatorTheme {
        Box(
            modifier = Modifier.padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            ScafiSpinner(
                header = "header",
                color = Color.Green,
                footer = "Footer"
            )
        }
    }
}

@Preview(name = "ScafiDivider")
@Composable
private fun ScafiDividerPreview() {
    ScafiDivider()
}