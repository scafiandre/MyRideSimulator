package com.scafisystems.myridesimulator.ui.components.icon


import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiIconColors
import com.scafisystems.myridesimulator.ui.components.core.tokens.ScafiIconSize

@Composable
fun ScafiIcon(
    resourceId: Int,
    modifier: Modifier = Modifier,
    color: Color = ScafiIconColors.default,
    size: ScafiIconSize = ScafiIconSize.Medium,
    contentDescription: String = "Icon"
) {

    val painter: Painter = painterResource(resourceId)

    Icon(
        painter = painter,
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier.requiredSize(size.value.dp)
    )

}

@Composable
fun ScafiIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
    color: Color = ScafiIconColors.default,
    size: ScafiIconSize = ScafiIconSize.Medium,
    contentDescription: String = "Icon"
) {
    Icon(
        painter = painter,
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier.requiredSize(size.value.dp)
    )

}

@Composable
fun ScafiIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    color: Color = ScafiIconColors.default,
    size: ScafiIconSize = ScafiIconSize.Medium,
    contentDescription: String = "Icon"
) {
    Icon(
        painter = rememberVectorPainter(imageVector),
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier.requiredSize(size.value.dp)
    )

}

@Composable
fun ScafiIcon(
    bitmap: ImageBitmap,
    modifier: Modifier = Modifier,
    color: Color = ScafiIconColors.default,
    size: ScafiIconSize = ScafiIconSize.Medium,
    contentDescription: String = "Icon"
) {
    val painter = remember(bitmap) { BitmapPainter(bitmap) }
    Icon(
        painter = painter,
        tint = color,
        contentDescription = contentDescription,
        modifier = modifier.requiredSize(size.value.dp)
    )

}

@Preview(name = "ScafiIcon")
@Composable
private fun ScafiIconPreview() {
    ScafiIcon(Icons.Outlined.AccountCircle, color = Color.White)
}