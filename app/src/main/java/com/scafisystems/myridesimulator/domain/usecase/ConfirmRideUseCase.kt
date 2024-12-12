package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.model.RideConfirmation
import com.scafisystems.myridesimulator.domain.model.RideRequest

class ConfirmRideUseCase(
    private val dataSource: MyRideDatasource,
) {
    suspend operator fun invoke(request: RideRequest): RideConfirmation =
        dataSource.confirmRide(request)
}