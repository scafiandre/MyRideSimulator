package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.helper.expectedHistory
import com.scafisystems.myridesimulator.helper.rideRequestHistory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetRidesUseCaseTest {

    private val dataSource = mockk<MyRideDatasource>()
    private val getRidesUseCase = GetRidesUseCase(dataSource)

    @Test
    fun `should return ride history when invoked`() = runBlocking {

        coEvery { dataSource.getRides(rideRequestHistory) } returns expectedHistory

        // Act
        val result = getRidesUseCase.invoke(rideRequestHistory)

        // Assert
        assertEquals(expectedHistory, result)
        assertEquals(expectedHistory.customer_id, result.customer_id)
        coVerify { dataSource.getRides(rideRequestHistory) }
    }
}