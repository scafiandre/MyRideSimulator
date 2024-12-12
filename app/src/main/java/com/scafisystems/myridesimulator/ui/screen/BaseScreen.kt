package com.scafisystems.myridesimulator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.scafisystems.myridesimulator.R
import com.scafisystems.myridesimulator.ui.components.loading.ScafiLoading
import com.scafisystems.myridesimulator.ui.components.toast.ScafiToast
import com.scafisystems.myridesimulator.ui.model.ScreenState

@Composable
fun BaseScreen(
    state: ScreenState?,
    onSuccessState: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = stringResource(id = R.string.error),
    successMessage: String = "",
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }

    if (state == ScreenState.LOADING) {
        ScafiLoading()
    }
    if (state == ScreenState.ERROR) {
        if (errorMessage.isNullOrBlank().not()) {
            ScafiToast(text = errorMessage!!)
        }

    }
    if (state == ScreenState.API_SUCCESS) {
        if (successMessage.isBlank().not()) {
            ScafiToast(text = successMessage)
        }
        onSuccessState()
    }
}

