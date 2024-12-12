package com.scafisystems.myridesimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.scafisystems.myridesimulator.ui.navigation.NavigationGraph
import com.scafisystems.myridesimulator.ui.theme.MyRideSimulatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRideSimulatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavigationGraphPreview() {
    MyRideSimulatorTheme {
        NavigationGraph()
    }
}