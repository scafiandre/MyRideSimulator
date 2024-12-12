package com.scafisystems.myridesimulator.ui.model

import com.scafisystems.myridesimulator.domain.model.Ride

data class TripUi(
    val date: String,
    val driverName: String,
    val origin: String,
    val destination: String,
    val distance: String,
    val duration: String,
    val value: String
)

fun Ride.mapToTripUi(): TripUi =
    TripUi(
        date = this.date,
        driverName = this.driver.name,
        origin = this.origin,
        destination = this.destination,
        distance = this.distance.toString(),
        duration = this.duration,
        value = this.value.toString()
    )

