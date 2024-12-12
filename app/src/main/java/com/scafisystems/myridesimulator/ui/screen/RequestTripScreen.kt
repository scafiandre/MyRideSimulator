package com.scafisystems.myridesimulator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.scafisystems.myridesimulator.R
import com.scafisystems.myridesimulator.ui.components.button.ScafiButton
import com.scafisystems.myridesimulator.ui.components.inputField.ScafiInputField
import com.scafisystems.myridesimulator.ui.components.spacer.ScafiVerticalSpacer
import com.scafisystems.myridesimulator.ui.components.text.ScafiText
import com.scafisystems.myridesimulator.ui.model.ScreenState
import com.scafisystems.myridesimulator.ui.navigation.Routes
import com.scafisystems.myridesimulator.ui.theme.MyRideSimulatorTheme
import com.scafisystems.myridesimulator.ui.viewModel.MyRideViewModel

@Composable
fun RequestTripScreen(
    navController: NavController,
    viewModel: MyRideViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.screenState.observeAsState()

    var customerId by remember { mutableStateOf("") }
    var origin by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    var customerIdErrorText by remember { mutableStateOf("") }
    var originErrorText by remember { mutableStateOf("") }
    var destinationErrorText by remember { mutableStateOf("") }

    val context = LocalContext.current

    fun validateForm(): Boolean {
        customerIdErrorText =
            if (customerId.isEmpty()) context.getString(R.string.please_fill_me) else ""
        originErrorText =
            if (origin.isEmpty()) context.getString(R.string.please_fill_me) else ""
        destinationErrorText =
            if (destination.isEmpty()) context.getString(R.string.please_fill_me) else ""

        if (origin == destination && origin.isEmpty().not() && destination.isEmpty().not()) {
            originErrorText =
                context.getString(R.string.it_is_not_allowed_the_same_address_for_origin_and_destination)
            destinationErrorText =
                context.getString(R.string.it_is_not_allowed_the_same_address_for_origin_and_destination)
        }


        return (customerIdErrorText.isEmpty()
                && originErrorText.isEmpty()
                && destinationErrorText.isEmpty())
    }

    BaseScreen(
        state = state,
        onSuccessState = {
            viewModel.setIdleState()
            navController.navigate(route = Routes.TripOptionsScreen.name) {
                popUpTo(Routes.HomeScreen.name) { inclusive = false }
                launchSingleTop = true
            }
        },
        errorMessage = viewModel.errorMessage.value,
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ScafiText(text = stringResource(R.string.request_a_trip), fontSize = 28)
                ScafiVerticalSpacer(48.dp)
                ScafiInputField(
                    text = customerId,
                    onTextChange = { customerId = it },
                    label = stringResource(id = R.string.Inform_your_id),
                    errorText = customerIdErrorText
                )
                ScafiVerticalSpacer()
                ScafiInputField(
                    text = origin,
                    onTextChange = { origin = it },
                    label = stringResource(R.string.from_address),
                    errorText = originErrorText
                )
                ScafiVerticalSpacer()
                ScafiInputField(
                    text = destination,
                    onTextChange = { destination = it },
                    label = stringResource(R.string.to_address),
                    errorText = destinationErrorText
                )
                ScafiVerticalSpacer(24.dp)
                ScafiButton(
                    text = stringResource(R.string.estimated_trip),
                    enabled = state != ScreenState.LOADING,
                    onClick = {
                        if (validateForm()) {
                            viewModel.requestRideEstimate(customerId, origin, destination)
                        }
                    })
            }
        }
    }
}


@Preview(name = "RequestTripScreen")
@Composable
private fun PreviewRequestTripScreen() {
    MyRideSimulatorTheme {
        RequestTripScreen(
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}