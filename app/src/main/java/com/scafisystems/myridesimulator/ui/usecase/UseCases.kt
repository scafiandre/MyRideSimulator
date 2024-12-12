package com.scafisystems.myridesimulator.ui.usecase

import com.scafisystems.myridesimulator.domain.usecase.ConfirmRideUseCase
import com.scafisystems.myridesimulator.domain.usecase.GetDriversUseCase
import com.scafisystems.myridesimulator.domain.usecase.GetEstimateRideUseCase
import com.scafisystems.myridesimulator.domain.usecase.GetRidesUseCase
import javax.inject.Inject

class UseCases @Inject constructor(
    val getEstimateRideUseCase: GetEstimateRideUseCase,
    val confirmRideUseCase: ConfirmRideUseCase,
    val getRidesUseCase: GetRidesUseCase,
    val getDriversUseCase: GetDriversUseCase
)