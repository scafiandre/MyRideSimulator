package com.scafisystems.myridesimulator.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.scafisystems.myridesimulator.helper.LiveDataHelper.getOrAwaitValue
import com.scafisystems.myridesimulator.helper.expectedEstimate
import com.scafisystems.myridesimulator.helper.rideRequestEstimate
import com.scafisystems.myridesimulator.ui.model.ScreenState
import com.scafisystems.myridesimulator.ui.usecase.UseCases
import com.scafisystems.myridesimulator.ui.viewModel.MyRideViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.Executors

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MyRideViewModelTest {

    @get:Rule
    var rule: TestRule =
        InstantTaskExecutorRule()

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor()
        .asCoroutineDispatcher()

    private lateinit var viewModel: MyRideViewModel
    private lateinit var useCases: UseCases

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        useCases = mockk()
        viewModel = MyRideViewModel(useCases)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `should load ride estimate successfully`() {

        coEvery { useCases.getEstimateRideUseCase.invoke(rideRequestEstimate) } returns expectedEstimate

        viewModel.requestRideEstimate(
            rideRequestEstimate.customer_id,
            rideRequestEstimate.origin,
            rideRequestEstimate.destination
        )

        assertEquals(ScreenState.API_SUCCESS, viewModel.screenState.getOrAwaitValue())
        assertEquals(expectedEstimate, viewModel.rideEstimateResponse.getOrAwaitValue())
    }

    //Todo: add more tests for cover all viewModel
}
