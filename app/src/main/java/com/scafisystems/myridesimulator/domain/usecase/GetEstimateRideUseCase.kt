package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.model.RideEstimate
import com.scafisystems.myridesimulator.domain.model.RideRequestEstimate

class GetEstimateRideUseCase(
    private val dataSource: MyRideDatasource,
) {
    suspend operator fun invoke(request: RideRequestEstimate): RideEstimate =
        dataSource.estimateRide(request)
}