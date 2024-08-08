package dev.zuwu.weather.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.ui.CurrentWeatherUI
import dev.zuwu.weather.ui.ForecastWeatherUI
import dev.zuwu.weather.ui.TodayWeatherUI
import dev.zuwu.weather.ui.TopBar
import dev.zuwu.weather.utils.LocationUtils
import dev.zuwu.weather.viewmodels.ForecastViewModel
import dev.zuwu.weather.viewmodels.LocationViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun MainWeatherScreen(locUtil: LocationUtils, forecastVM: ForecastViewModel, locationViewModel: LocationViewModel, navToSettings: () -> Unit) {
    var barPadding = remember { mutableStateOf(PaddingValues(0.dp)) }
    val location = locationViewModel.location.value
    val forecast = forecastVM.weatherData.value
    val search = forecastVM.searchData.collectAsState()
    val isSearching = forecastVM.isLoading.collectAsState()

    TopBar(
        refresh = {
        locUtil.getCurrentLocation()
        runBlocking { forecastVM.getWeatherData("${location?.lat},${location?.lon}") } },
        barPadding,
        navToSettings,
        onQueryChange = { runBlocking { forecastVM.getSearchData(it) } },
        onSearch = { runBlocking { forecastVM.getWeatherData(it) } },
        onActiveChange = { },
        items = search,
        isSearching = isSearching
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(barPadding.value)) {
        Row(modifier = Modifier
            .height(128.dp)
            .fillMaxWidth()) {
            CurrentWeatherUI(forecast = forecast)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            TodayWeatherUI(forecast = forecast)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            ForecastWeatherUI(forecast = forecast)
        }
    }
}