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
import com.scafisystems.myridesimulator.ui.components.dropDownMenu.ScafiDropDownMenu
import com.scafisystems.myridesimulator.ui.components.inputField.ScafiInputField
import com.scafisystems.myridesimulator.ui.components.itemList.TripItem
import com.scafisystems.myridesimulator.ui.components.list.ScafiVerticalList
import com.scafisystems.myridesimulator.ui.components.spacer.ScafiVerticalSpacer
import com.scafisystems.myridesimulator.ui.components.text.ScafiText
import com.scafisystems.myridesimulator.ui.model.ScreenState
import com.scafisystems.myridesimulator.ui.navigation.Routes
import com.scafisystems.myridesimulator.ui.theme.MyRideSimulatorTheme
import com.scafisystems.myridesimulator.ui.viewModel.MyRideViewModel

@Composable
fun HistoryTripScreen(
    navController: NavController,
    viewModel: MyRideViewModel,
    modifier: Modifier = Modifier,
) {
    val driversList by viewModel.driversListSelection.observeAsState(listOf())
    val tripList by viewModel.optionTripUiList.observeAsState(listOf())
    var userId by remember { mutableStateOf(viewModel.getCustomerId()) }
    var selectedDriver by remember { mutableStateOf(driversList[0]) }
    var errorText by remember { mutableStateOf("") }

    val state by viewModel.screenState.observeAsState()

    val context = LocalContext.current

    BaseScreen(
        state = state,
        onSuccessState = {
            viewModel.setIdleState()
        },
        errorMessage = viewModel.errorMessage.value,
        successMessage = stringResource(R.string.list_uploaded_successful)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScafiText(text = stringResource(R.string.history_trip), fontSize = 28)
            ScafiVerticalSpacer(48.dp)

            ScafiInputField(
                text = userId,
                onTextChange = { userId = it },
                label = stringResource(R.string.Inform_your_id),
                errorText = errorText
            )
            ScafiVerticalSpacer()
            ScafiDropDownMenu(
                options = driversList,
                selectedOption = selectedDriver,
                onOptionSelected = { selectedDriver = it },
                label = stringResource(R.string.choose_a_driver)
            )
            ScafiVerticalSpacer()
            ScafiButton(
                text = stringResource(R.string.search_trips),
                enabled = state != ScreenState.LOADING,
                onClick = {
                    if (userId.isEmpty()) {
                        errorText = context.getString(R.string.please_fill_me)
                    } else {
                        errorText = ""
                        viewModel.setRideHistoryList(userId, selectedDriver)
                    }

                }
            )
            ScafiVerticalSpacer()

            Box(modifier = Modifier.weight(1f)) {
                ScafiVerticalList(
                    items = tripList,
                    emptyListText = viewModel.emptyListMessage.value
                ) {
                    TripItem(tripUi = it)
                }
            }

            ScafiButton(
                text = stringResource(R.string.back_home_screen),
                onClick = {
                    navController.navigate(route = Routes.HomeScreen.name) {
                        popUpTo(Routes.HomeScreen.name) { inclusive = false }
                        launchSingleTop = true
                    }
                })
        }
    }
}

@Preview(name = "HistoryTripScreen")
@Composable
private fun PreviewHistoryTripScreen() {
    MyRideSimulatorTheme {
        MyRideSimulatorTheme {
            HistoryTripScreen(
                navController = rememberNavController(),
                viewModel = hiltViewModel()
            )
        }
    }
}