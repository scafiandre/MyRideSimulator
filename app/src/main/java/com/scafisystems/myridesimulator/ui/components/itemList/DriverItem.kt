package com.scafisystems.myridesimulator.ui.components.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scafisystems.myridesimulator.R
import com.scafisystems.myridesimulator.ui.components.spacer.ScafiVerticalSpacer
import com.scafisystems.myridesimulator.ui.model.DriverUI

@Composable
fun DriverItem(
    driverUI: DriverUI,
    onChoose: (DriverUI) -> Unit,
    modifier: Modifier = Modifier,
    enabledButton: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = driverUI.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            ScafiVerticalSpacer(4.dp)

            Text(
                text = driverUI.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            ScafiVerticalSpacer(4.dp)

            Text(
                text = stringResource(R.string.vehicle, driverUI.vehicle),
                style = MaterialTheme.typography.bodyMedium
            )

            ScafiVerticalSpacer(4.dp)

            Text(
                text = stringResource(R.string.rating, driverUI.rating),
                style = MaterialTheme.typography.bodyMedium
            )

            ScafiVerticalSpacer(4.dp)

            Text(
                text = stringResource(R.string.price_r, driverUI.price),
                style = MaterialTheme.typography.bodyMedium
            )

            ScafiVerticalSpacer(4.dp)

            Button(
                onClick = { onChoose(driverUI) },
                modifier = Modifier.align(Alignment.End),
                enabled = enabledButton
            ) {
                Text(text = stringResource(R.string.choose))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DriverItemPreview() {
    val driverUI = DriverUI(
        1,
        "João Silva",
        "Experiente e confiável",
        "Sedan - Toyota Corolla",
        "4.9", "25.00"
    )
    DriverItem(driverUI, {})
}