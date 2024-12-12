package com.scafisystems.myridesimulator.ui.components.toast

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScafiToast(
    text: String,
) {

    val context = LocalContext.current

    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

}

@Preview(name = "ScafiToast")
@Composable
private fun PreviewScafiToast() {
    ScafiToast("test")
}