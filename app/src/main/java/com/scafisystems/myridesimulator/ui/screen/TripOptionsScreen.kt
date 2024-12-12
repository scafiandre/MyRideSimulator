package com.scafisystems.myridesimulator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scafisystems.myridesimulator.R
import com.scafisystems.myridesimulator.ui.components.itemList.DriverItem
import com.scafisystems.myridesimulator.ui.components.list.ScafiVerticalList
import com.scafisystems.myridesimulator.ui.components.map.ScafiGoogleStaticMap
import com.scafisystems.myridesimulator.ui.components.spacer.ScafiVerticalSpacer
import com.scafisystems.myridesimulator.ui.components.text.ScafiText
import com.scafisystems.myridesimulator.ui.model.ScreenState
import com.scafisystems.myridesimulator.ui.navigation.Routes
import com.scafisystems.myridesimulator.ui.theme.MyRideSimulatorTheme
import com.scafisystems.myridesimulator.ui.utils.toKm
import com.scafisystems.myridesimulator.ui.utils.toMinutes
import com.scafisystems.myridesimulator.ui.viewModel.MyRideViewModel

@Composable
fun TripOptionsScreen(
    navController: NavController,
    viewModel: MyRideViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.screenState.observeAsState()

    BaseScreen(
        state = state,
        onSuccessState = {
            viewModel.setIdleState()
            navController.navigate(route = Routes.TripHistoryScreen.name) {
                popUpTo(Routes.HomeScreen.name) { inclusive = false }
                launchSingleTop = true
            }
        },
        errorMessage = viewModel.errorMessage.value,
        successMessage = stringResource(R.string.viagem_confirmada)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val mapCoordinates = viewModel.rideEstimateResponse.value!!
            ScafiText(text = stringResource(R.string.choose_the_driver), fontSize = 28)
            ScafiVerticalSpacer(48.dp)

            ScafiGoogleStaticMap(
                originLatitude = mapCoordinates.origin.latitude,
                originLongitude = mapCoordinates.origin.longitude,
                destLatitude = mapCoordinates.destination.latitude,
                destLongitude = mapCoordinates.destination.longitude,
            )
            Row(
                Modifier
                    .padding(vertical = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ScafiText(
                    text = stringResource(
                        R.string.distance_km,
                        viewModel.rideEstimateResponse.value!!.distance.toKm()
                    ),
                )
                ScafiText(
                    text = stringResource(
                        R.string.duration_min,
                        viewModel.rideEstimateResponse.value!!.duration.toMinutes()
                    ),
                )
            }

            ScafiVerticalList(
                items = viewModel.getOptionDriveList(),
                emptyListText = viewModel.emptyListMessage.value
            ) {
                DriverItem(
                    driverUI = it,
                    enabledButton = state != ScreenState.LOADING,
                    onChoose = { driver ->
                        viewModel.requestConfirmTrip(driver.id)
                    })
            }
        }
    }
}


@Preview(name = "HistoryTripScreen")
@Composable
private fun PreviewTripOptionsScreen() {
    MyRideSimulatorTheme {
        TripOptionsScreen(
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}