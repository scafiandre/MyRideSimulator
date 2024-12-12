package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.model.Driver

class GetDriversUseCase(
    private val dataSource: MyRideDatasource,
) {
    suspend operator fun invoke(): List<Driver> = dataSource.getDrivers()
}