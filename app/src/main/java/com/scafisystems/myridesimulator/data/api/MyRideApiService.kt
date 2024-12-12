package com.scafisystems.myridesimulator.data.api

import com.scafisystems.myridesimulator.domain.model.RideConfirmation
import com.scafisystems.myridesimulator.domain.model.RideEstimate
import com.scafisystems.myridesimulator.domain.model.RideHistory
import com.scafisystems.myridesimulator.domain.model.RideRequest
import com.scafisystems.myridesimulator.domain.model.RideRequestEstimate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyRideApiService {

    @POST("/ride/estimate")
    suspend fun estimateRide(@Body request: RideRequestEstimate): Response<RideEstimate>

    @PATCH("/ride/confirm")
    suspend fun confirmRide(@Body request: RideRequest): Response<RideConfirmation>

    @GET("/ride/{customer_id}")
    suspend fun getRides(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: Int?
    ): Response<RideHistory>
}