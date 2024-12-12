package com.scafisystems.myridesimulator.domain.model

data class RideRequestEstimate(
    val customer_id: String,
    val origin: String,
    val destination: String
)

data class RideEstimate(
    val origin: Location,
    val destination: Location,
    val distance: Double,
    val duration: String,
    val options: List<RideOption>
)

data class RideRequest(
    val customer_id: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

data class RideConfirmation(
    val success: Boolean,
)

data class RideRequestHistory(
    val customerId: String,
    val driverId: Int?
)

data class RideHistory(
    val customer_id: String,
    val rides: List<Ride>
)

data class RideOption(
    val id: Int,
    val name: String,
    val description: String,
    val vehicle: String,
    val review: Review,
    val value: Double
)

data class Driver(
    val id: Int,
    val name: String,
    val minMetersAccepted: Double = 0.0,
)

data class Ride(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: Driver,
    val value: Double
)

data class Review(
    val rating: Double,
    val comment: String
)

data class Location(
    val latitude: Double,
    val longitude: Double
)
