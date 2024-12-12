package com.scafisystems.myridesimulator.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> ScafiVerticalList(
    items: List<T>,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    verticalSpacing: Int = 8,
    modifier: Modifier = Modifier,
    emptyListText: String? = "Empty List",
    itemContent: @Composable (T) -> Unit
) {
    if (items.isNotEmpty()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp),
            contentPadding = contentPadding,
            modifier = modifier
        ) {
            items(items.size) { index ->
                itemContent(items[index])
            }
        }
    } else {
        if (emptyListText != null) {
            Text(
                text = emptyListText,
                modifier = modifier
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ScafiVerticalListPreview() {
    val nameList = listOf("Marcio", "Pedro", "Juliana")
    ScafiVerticalList(items = nameList) { name ->
        Text(text = name)
    }
}