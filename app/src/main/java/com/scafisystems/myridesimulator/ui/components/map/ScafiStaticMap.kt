package com.scafisystems.myridesimulator.ui.components.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.scafisystems.myridesimulator.ui.components.spinner.ScafiSpinner
import com.scafisystems.myridesimulator.ui.utils.Constants.GOOGLE_KEY


@Composable
fun ScafiGoogleStaticMap(
    originLatitude: Double,
    originLongitude: Double,
    destLatitude: Double,
    destLongitude: Double,
    apiKey: String = GOOGLE_KEY,
    modifier: Modifier = Modifier
) {

    val mapUrl = "https://maps.googleapis.com/maps/api/staticmap" +
            "?size=600x300" +
            "&markers=color:blue|label:A|$originLatitude,$originLongitude" +
            "&markers=color:red|label:B|$destLatitude,$destLongitude" +
            "&path=color:0xff0000ff|weight:5|$originLatitude,$originLongitude|$destLatitude,$destLongitude" +
            "&key=$apiKey"

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        ScafiSpinner()
        Image(
            painter = rememberAsyncImagePainter(mapUrl),
            contentDescription = "Mapa com a rota",
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(2f)
        )
    }

}