package com.scafisystems.myridesimulator.domain.usecase

import com.scafisystems.myridesimulator.domain.datasource.MyRideDatasource
import com.scafisystems.myridesimulator.helper.expectedDrivers
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class GetDriversUseCaseTest {

    private val dataSource = mockk<MyRideDatasource>()
    private val getDriversUseCase = GetDriversUseCase(dataSource)

    @Test
    fun `should return list of drivers when invoked`() = runBlocking {

        coEvery { dataSource.getDrivers() } returns expectedDrivers

        val result = getDriversUseCase.invoke()

        assertEquals(expectedDrivers, result)
        assertEquals(2, result.size)
        coVerify { dataSource.getDrivers() }
    }
}