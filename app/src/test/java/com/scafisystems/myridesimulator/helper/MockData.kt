package com.scafisystems.myridesimulator.helper

import com.scafisystems.myridesimulator.domain.model.Driver
import com.scafisystems.myridesimulator.domain.model.Location
import com.scafisystems.myridesimulator.domain.model.Review
import com.scafisystems.myridesimulator.domain.model.Ride
import com.scafisystems.myridesimulator.domain.model.RideEstimate
import com.scafisystems.myridesimulator.domain.model.RideHistory
import com.scafisystems.myridesimulator.domain.model.RideOption
import com.scafisystems.myridesimulator.domain.model.RideRequest
import com.scafisystems.myridesimulator.domain.model.RideRequestEstimate
import com.scafisystems.myridesimulator.domain.model.RideRequestHistory

val rideRequest = RideRequest(
    customer_id = "123",
    origin = "Origin Address",
    destination = "Destination Address",
    distance = 10.0,
    duration = "15.0",
    driver = Driver(id = 1, name = "John Doe"),
    value = 25.0
)

val expectedDrivers = listOf(
    Driver(1, "Peter"),
    Driver(2, "Mark")
)

val rideRequestEstimate = RideRequestEstimate(
    "1", "Rua Augusta", "Av. Paulista"
)
val expectedEstimate = RideEstimate(
    Location(1.0, 2.0),
    Location(1.0, 2.0),
    20.0,
    "1:00",
    listOf(
        RideOption(
            1, "Aldo", "description", "gol", Review(1.0, "comment"), 100.0
        ),
        RideOption(
            2, "Peter", "description", "gol", Review(2.0, "comment"), 200.0
        ),

        )
)

val rideRequestHistory = RideRequestHistory("1", 1)
val expectedHistory = RideHistory(
    "1", listOf(
        Ride(
            1,
            "12/12/24",
            "Origin Address",
            "Destination Address",
            10.0,
            "1:00",
            Driver(1, "Peter"),
            10.0
        ),
        Ride(
            1,
            "12/10/24",
            "Origin Address",
            "Destination Address",
            10.0,
            "1:00",
            Driver(1, "Andre"),
            10.0
        )
    )
)