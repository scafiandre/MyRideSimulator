package com.scafisystems.myridesimulator.ui.components.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scafisystems.myridesimulator.R
import com.scafisystems.myridesimulator.ui.model.TripUi
import com.scafisystems.myridesimulator.ui.utils.toTwoChar

@Composable
fun TripItem(tripUi: TripUi) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.date, tripUi.date),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.driver, tripUi.driverName),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.origin, tripUi.origin),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.destination, tripUi.destination),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.distance_km_item, tripUi.distance),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.duration_min_item, tripUi.duration),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.value_r, tripUi.value.toTwoChar()),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TripItemPreview() {
    val tripUi = TripUi(
        "01/12/2023 10:30",
        "João Silva",
        "Av. Paulista",
        "Rua Augusta",
        "5km", "15min",
        "20.00"
    )
    TripItem(tripUi)
}