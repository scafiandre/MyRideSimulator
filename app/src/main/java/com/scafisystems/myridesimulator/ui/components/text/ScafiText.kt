package com.scafisystems.myridesimulator.ui.components.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiColorToken

@Composable
fun ScafiText(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = ScafiColorToken.DefaultColor.textBaseColor,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    maxLines: Int = Int.MAX_VALUE,
    softWrap: Boolean = true,
    fontSize: Int = 14
) {
    BasicText(
        text = text,
        modifier = modifier,
        onTextLayout = onTextLayout,
        maxLines = maxLines,
        overflow = overflow,
        softWrap = softWrap,
        style = TextStyle(
            color = contentColor,
            textAlign = textAlign,
            fontWeight = fontWeight,
            fontSize = fontSize.sp
        )
    )
}

@Preview(name = "ScafiText")
@Composable
private fun PreviewScafiText() {
    ScafiText("ScafiText")
}