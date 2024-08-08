package dev.zuwu.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.zuwu.weather.api.WeatherAPI
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.model.SearchResponseItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ForecastViewModel() : ViewModel() {

    private val _weatherData = MutableLiveData<ForecastResponse>()
    val weatherData: MutableLiveData<ForecastResponse> get() = _weatherData

    private val _searchData = MutableStateFlow<List<SearchResponseItem>>(listOf())
    val searchData: StateFlow<List<SearchResponseItem>> get() = _searchData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    var errorMessage: String = ""
        private set

    suspend fun getWeatherData(q: String) {

        _isLoading.value = true
        _isError.value = false

        val client = WeatherAPI()

        try {
            val response = client.fetchWeatherData(q)
            _weatherData.value = response
        } catch (e: Exception) {
            _isError.value = true
            errorMessage = e.message ?: "An error occurred"
        } finally {
            _isLoading.value = false
        }
    }

    suspend fun getSearchData(q: String) {

        _isLoading.value = true
        _isError.value = false

        val client = WeatherAPI()

        try {
            val response = client.fetchSearchData(q)
            _searchData.value = response
        } catch (e: Exception) {
            _isError.value = true
            errorMessage = e.message ?: "An error occurred"
        } finally {
            _isLoading.value = false
        }
    }
}