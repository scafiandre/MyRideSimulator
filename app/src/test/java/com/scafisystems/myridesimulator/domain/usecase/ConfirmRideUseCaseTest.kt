package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.domain.model.RideConfirmation
import com.scafisystems.myridesimulator.helper.rideRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ConfirmRideUseCaseTest {

    private val dataSource = mockk<MyRideDatasource>()
    private val confirmRideUseCase = ConfirmRideUseCase(dataSource)


    @Test
    fun `should confirm ride when invoked`() = runBlocking {
        val expectedConfirmation = RideConfirmation(true)

        coEvery { dataSource.confirmRide(rideRequest) } returns expectedConfirmation

        val result = confirmRideUseCase.invoke(rideRequest)

        assertEquals(expectedConfirmation, result)
        assertEquals(true, result.success)
        coVerify { dataSource.confirmRide(rideRequest) }
    }
}
