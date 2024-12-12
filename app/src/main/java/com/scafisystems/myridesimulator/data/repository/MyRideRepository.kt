package com.scafisystems.myridesimulator.data.repository

import com.scafisystems.myridesimulator.data.api.reqApi
import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.model.Driver
import com.scafisystems.myridesimulator.domain.model.RideConfirmation
import com.scafisystems.myridesimulator.domain.model.RideEstimate
import com.scafisystems.myridesimulator.domain.model.RideHistory
import com.scafisystems.myridesimulator.domain.model.RideRequest
import com.scafisystems.myridesimulator.domain.model.RideRequestEstimate
import com.scafisystems.myridesimulator.domain.model.RideRequestHistory

class MyRideRepository() : MyRideDatasource {
    override suspend fun estimateRide(request: RideRequestEstimate): RideEstimate {
        try {
            val response = reqApi.estimateRide(request)
            if (response.isSuccessful) {
                return response.body()
                    ?: throw IllegalStateException("RideEstimate response is null")
            } else {
                throw IllegalStateException(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            throw IllegalStateException(e.message)
        }
    }

    override suspend fun confirmRide(request: RideRequest): RideConfirmation {
        try {
            val response = reqApi.confirmRide(request)
            if (response.isSuccessful) {
                return response.body()
                    ?: throw IllegalStateException("RideConfirmation response is null")
            } else {
                throw IllegalStateException(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            throw IllegalStateException(e.message)
        }

    }

    override suspend fun getRides(request: RideRequestHistory): RideHistory {
        try {
            val response = reqApi.getRides(request.customerId, request.driverId)
            if (response.isSuccessful) {
                return response.body()
                    ?: throw IllegalStateException("RideHistory response is null")
            } else {
                throw IllegalStateException(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            throw IllegalStateException(e.message)
        }

    }

    override suspend fun getDrivers(): List<Driver> {
        return listOf(
            Driver(1, "Homer Simpson", 1000.0),
            Driver(2, "Dominic Toretto", 5000.0),
            Driver(3, "James Bond", 10000.0)
        )
    }
}