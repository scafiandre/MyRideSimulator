package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.model.RideHistory
import com.scafisystems.myridesimulator.domain.model.RideRequestHistory

class GetRidesUseCase(
    private val dataSource: MyRideDatasource,
) {
    suspend operator fun invoke(request: RideRequestHistory): RideHistory =
        dataSource.getRides(request)
}