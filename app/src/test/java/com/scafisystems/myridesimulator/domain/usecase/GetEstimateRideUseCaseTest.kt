package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.helper.expectedEstimate
import com.scafisystems.myridesimulator.helper.rideRequestEstimate
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class GetEstimateRideUseCaseTest {

    private val dataSource = mockk<MyRideDatasource>()
    private val getEstimateRideUseCase = GetEstimateRideUseCase(dataSource)

    @Test
    fun `should return ride estimate when invoked`() = runBlocking {

        coEvery { dataSource.estimateRide(rideRequestEstimate) } returns expectedEstimate

        val result = getEstimateRideUseCase.invoke(rideRequestEstimate)

        assertEquals(expectedEstimate, result)
        assertEquals(expectedEstimate.options[0], result.options[0])
        coVerify { dataSource.estimateRide(rideRequestEstimate) }
    }
}