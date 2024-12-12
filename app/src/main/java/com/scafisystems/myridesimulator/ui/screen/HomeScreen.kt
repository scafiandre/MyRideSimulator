package com.scafisystems.myridesimulator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scafisystems.myridesimulator.R
import com.scafisystems.myridesimulator.ui.components.button.ScafiButton
import com.scafisystems.myridesimulator.ui.components.spacer.ScafiVerticalSpacer
import com.scafisystems.myridesimulator.ui.components.text.ScafiText
import com.scafisystems.myridesimulator.ui.navigation.Routes
import com.scafisystems.myridesimulator.ui.theme.MyRideSimulatorTheme

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScafiText(text = stringResource(R.string.app_name), fontSize = 28)
        ScafiVerticalSpacer(48.dp)
        ScafiText(text = stringResource(R.string.choose_the_option_you_want))
        ScafiVerticalSpacer(24.dp)
        ScafiButton(text = stringResource(R.string.request_a_trip), onClick = {
            navController.navigate(route = Routes.TripRequestScreen.name) {
                popUpTo(Routes.HomeScreen.name) { inclusive = false }
                launchSingleTop = true
            }
        })
        ScafiVerticalSpacer()
        ScafiButton(text = stringResource(R.string.trip_history), onClick = {
            navController.navigate(route = Routes.TripHistoryScreen.name) {
                popUpTo(Routes.HomeScreen.name) { inclusive = false }
                launchSingleTop = true
            }
        })
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    MyRideSimulatorTheme {
        MyRideSimulatorTheme {
            HomeScreen(navController = rememberNavController())
        }
    }
}