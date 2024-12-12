package com.scafisystems.myridesimulator.ui.model

import com.scafisystems.myridesimulator.domain.model.RideOption

data class DriverUI(
    val id: Int,
    val name: String,
    val description: String,
    val vehicle: String,
    val rating: String,
    val price: String
)

fun RideOption.mapToDriveUi(): DriverUI =
    DriverUI(
        id = this.id,
        name = this.name,
        description = this.description,
        vehicle = this.vehicle,
        rating = this.review.rating.toString(),
        price = this.value.toString()
    )