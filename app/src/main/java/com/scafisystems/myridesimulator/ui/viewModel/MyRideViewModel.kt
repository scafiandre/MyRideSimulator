package com.scafisystems.myridesimulator.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scafisystems.myridesimulator.domain.model.Driver
import com.scafisystems.myridesimulator.domain.model.RideConfirmation
import com.scafisystems.myridesimulator.domain.model.RideEstimate
import com.scafisystems.myridesimulator.domain.model.RideHistory
import com.scafisystems.myridesimulator.domain.model.RideRequest
import com.scafisystems.myridesimulator.domain.model.RideRequestEstimate
import com.scafisystems.myridesimulator.domain.model.RideRequestHistory
import com.scafisystems.myridesimulator.ui.model.DriverUI
import com.scafisystems.myridesimulator.ui.model.ScreenState
import com.scafisystems.myridesimulator.ui.model.TripUi
import com.scafisystems.myridesimulator.ui.model.mapToDriveUi
import com.scafisystems.myridesimulator.ui.model.mapToTripUi
import com.scafisystems.myridesimulator.ui.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRideViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _screenState = MutableLiveData(ScreenState.IDLE)
    val screenState: LiveData<ScreenState> get() = _screenState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _emptyListMessage = MutableLiveData<String>("Empty List")
    val emptyListMessage: LiveData<String> get() = _emptyListMessage

    private val _rideEstimateResponse = MutableLiveData<RideEstimate>()
    val rideEstimateResponse: LiveData<RideEstimate> get() = _rideEstimateResponse

    private val _rideRequestEstimate = MutableLiveData<RideRequestEstimate>()
    private val _rideConfirmResponse = MutableLiveData<RideConfirmation>()
    private val _rideOptionRequest = MutableLiveData<RideRequest>()
    private val _rideHistoryResponse = MutableLiveData<RideHistory>()
    private val _driversList = MutableLiveData<List<Driver>>()

    private val _driversListSelection = MutableLiveData<List<String>>()
    val driversListSelection: LiveData<List<String>> get() = _driversListSelection

    private val _optionTripUiList = MutableLiveData<List<TripUi>>()
    val optionTripUiList: LiveData<List<TripUi>> get() = _optionTripUiList

    init {
        setDriversListName()
    }

    fun requestRideEstimate(
        customer_id: String,
        origin: String,
        destination: String
    ) = viewModelScope.launch {

        _screenState.value = ScreenState.LOADING

        _rideRequestEstimate.value = RideRequestEstimate(
            customer_id,
            origin,
            destination
        )

        _rideRequestEstimate.value?.let { request ->
            try {
                val rideEstimate = useCases.getEstimateRideUseCase.invoke(request)
                _rideEstimateResponse.value = rideEstimate
                _screenState.value = ScreenState.API_SUCCESS
                if (rideEstimate.options.isEmpty())
                    _emptyListMessage.value = "NO DRIVERS FOUND FOR THIS ADDRESS"
                else
                    _emptyListMessage.value = "EMPTY LIST"
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
                _screenState.value = ScreenState.ERROR
            }
        } ?: run {
            _errorMessage.value = "Ride request is null"
            _screenState.value = ScreenState.ERROR
        }

    }

    fun getOptionDriveList(): List<DriverUI> {
        return rideEstimateResponse.value?.options?.map { rideOption ->
            rideOption.mapToDriveUi()
        } ?: listOf()
    }

    fun requestConfirmTrip(
        driverId: Int,
    ) = viewModelScope.launch {
        _screenState.value = ScreenState.LOADING

        val rideOption = rideEstimateResponse.value!!.options.first { it.id == driverId }

        _rideOptionRequest.value = RideRequest(
            customer_id = _rideRequestEstimate.value!!.customer_id,
            origin = _rideRequestEstimate.value!!.origin,
            destination = _rideRequestEstimate.value!!.destination,
            distance = rideEstimateResponse.value!!.distance,
            duration = rideEstimateResponse.value!!.duration,
            driver = Driver(
                id = rideOption.id,
                name = rideOption.name
            ),
            value = rideOption.value
        )

        if (validatedConfirmTrip(_rideOptionRequest.value!!)) {

            _rideOptionRequest.value?.let { request ->

                try {
                    val rideConfirm = useCases.confirmRideUseCase.invoke(request)
                    _rideConfirmResponse.value = rideConfirm
                    if (rideConfirm.success) {
                        _screenState.value = ScreenState.API_SUCCESS
                    } else {
                        _errorMessage.postValue("Error! Try again")
                        _screenState.value = ScreenState.ERROR
                    }
                } catch (e: Exception) {
                    _errorMessage.postValue(e.message)
                    _screenState.value = ScreenState.ERROR
                }
            } ?: run {
                _errorMessage.postValue("RideOptionRequest request is null")
                _screenState.value = ScreenState.ERROR
            }
        } else {
            _errorMessage.postValue("Driver nor accept this Distance")
            _screenState.value = ScreenState.ERROR
        }
    }


    fun setIdleState() {
        _screenState.value = ScreenState.IDLE
    }

    fun getCustomerId(): String {
        return _rideRequestEstimate.value?.customer_id ?: ""
    }

    fun setRideHistoryList(customerId: String, driverName: String) = viewModelScope.launch {

        _screenState.value = ScreenState.LOADING

        val driverId = _driversList.value?.first {
            it.name == driverName
        }?.id ?: 1

        val request = RideRequestHistory(
            customerId = customerId,
            driverId = driverId,
        )

        if (validatedRequestHistoryList(request)) {

            try {
                val response = useCases.getRidesUseCase.invoke(request)

                _rideHistoryResponse.value = filterHistoryList(driverName, response)

                setOptionTripUiList()

                _screenState.value = ScreenState.API_SUCCESS
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)

                if (e.message?.contains("NO_RIDES_FOUND") == true) {
                    _errorMessage.value = "NO RIDES FOUND"
                    _emptyListMessage.value = "NO RIDES FOUND"
                    _optionTripUiList.value = listOf()
                }
                _screenState.value = ScreenState.ERROR
            }
        } else {
            _errorMessage.postValue("Invalid insert data!")
            _screenState.value = ScreenState.ERROR
        }
    }

    private fun setDriversListName() = viewModelScope.launch {
        _driversList.value = useCases.getDriversUseCase.invoke()
        _driversListSelection.value = _driversList.value!!.map { it.name }
    }

    private fun setOptionTripUiList() {
        _optionTripUiList.value = _rideHistoryResponse.value?.rides?.map { ride ->
            ride.mapToTripUi()
        } ?: listOf()
    }

    private fun filterHistoryList(driverName: String, response: RideHistory): RideHistory {
        return response.copy(
            rides = response.rides.filter { it.driver.name == driverName }
        )
    }

    private fun validatedRequestHistoryList(request: RideRequestHistory): Boolean {
        if (request.customerId.isNullOrEmpty()) return false
        if (_driversList.value!!.any { it.id == request.driverId }.not()) return false
        return true
    }

    private fun validatedConfirmTrip(request: RideRequest): Boolean {
        val driveMinMeterAccept =
            _driversList.value?.first { it.id == request.driver.id }?.minMetersAccepted
        return request.distance >= driveMinMeterAccept!!
    }

}
