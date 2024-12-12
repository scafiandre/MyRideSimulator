package com.scafisystems.myridesimulator.di

import com.scafisystems.myridesimulator.data.repository.MyRideRepository
import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.usecase.ConfirmRideUseCase
import com.scafisystems.myridesimulator.domain.usecase.GetDriversUseCase
import com.scafisystems.myridesimulator.domain.usecase.GetEstimateRideUseCase
import com.scafisystems.myridesimulator.domain.usecase.GetRidesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMyRideRepository(): MyRideDatasource {
        return MyRideRepository()
    }

    @Provides
    @Singleton
    fun providesConfirmRideUseCase(dataSource: MyRideDatasource): ConfirmRideUseCase {
        return ConfirmRideUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetEstimateRideUseCase(dataSource: MyRideDatasource): GetEstimateRideUseCase {
        return GetEstimateRideUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetRidesUseCase(dataSource: MyRideDatasource): GetRidesUseCase {
        return GetRidesUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetDriversUseCase(dataSource: MyRideDatasource): GetDriversUseCase {
        return GetDriversUseCase(dataSource)
    }
}