package com.scafisystems.myridesimulator.domain.datasource

import com.scafisystems.myridesimulator.domain.model.Driver
import com.scafisystems.myridesimulator.domain.model.RideConfirmation
import com.scafisystems.myridesimulator.domain.model.RideEstimate
import com.scafisystems.myridesimulator.domain.model.RideHistory
import com.scafisystems.myridesimulator.domain.model.RideRequest
import com.scafisystems.myridesimulator.domain.model.RideRequestEstimate
import com.scafisystems.myridesimulator.domain.model.RideRequestHistory

interface MyRideDatasource {
    suspend fun estimateRide(request: RideRequestEstimate): RideEstimate
    suspend fun confirmRide(request: RideRequest): RideConfirmation
    suspend fun getRides(request: RideRequestHistory): RideHistory
    suspend fun getDrivers():  List<Driver>
}