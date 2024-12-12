package com.scafisystems.myridesimulator.ui.components.inputField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiInputFieldSize

@Composable
fun ScafiInputField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: ScafiInputFieldSize = ScafiInputFieldSize.Medium,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    label: String? = null,
    supportText: String? = null,
    errorText: String? = null,
    placeHolder: String? = null,
    readyOnly: Boolean = false,
    disabled: Boolean = false,
    hideCounter: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val isError = errorText.isNullOrEmpty().not()

    Column(modifier = modifier) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = if (isError) MaterialTheme.colorScheme.error else Color.Gray),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(size.inputWrapperHeight.dp)
                .background(
                    color = if (disabled) Color.LightGray else Color.White,
                    shape = MaterialTheme.shapes.small
                )
                .border(1.dp, Color.Black, MaterialTheme.shapes.small)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            if (placeHolder != null && text.isEmpty()) {
                Text(
                    text = placeHolder,
                    style = TextStyle(color = Color.Gray, fontSize = size.inputTextVariant.fontSize),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxLength) onTextChange(it)
                },
                modifier = Modifier.fillMaxSize(),
                textStyle = TextStyle.Default.copy(color = Color.Black, fontSize = size.inputTextVariant.fontSize),
                readOnly = readyOnly,
                enabled = !disabled,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                onTextLayout = onTextLayout,
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (leading != null) {
                            Box(modifier = Modifier.padding(end = 8.dp)) {
                                leading()
                            }
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            innerTextField()
                        }

                        if (trailing != null) {
                            Box(modifier = Modifier.padding(start = 8.dp)) {
                                trailing()
                            }
                        }
                    }
                }
            )
        }

        if (!hideCounter || isError) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isError) {
                    Text(
                        text = errorText.orEmpty(),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error)
                    )
                }

                if (!hideCounter) {
                    Text(
                        text = "${text.length} / $maxLength",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isError) MaterialTheme.colorScheme.error else Color.Gray
                    )
                }
            }
        }

        if (supportText != null && !isError) {
            Text(
                text = supportText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(name = "ScafiInputField")
@Composable
private fun ScafiInputFieldPreview() {
    ScafiInputField("Test", {})
}