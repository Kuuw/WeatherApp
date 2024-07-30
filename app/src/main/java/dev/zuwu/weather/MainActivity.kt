package dev.zuwu.weather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.ui.CurrentWeatherUI
import dev.zuwu.weather.ui.ForecastWeatherUI
import dev.zuwu.weather.ui.TodayWeatherUI
import dev.zuwu.weather.ui.theme.MaterialWeatherTheme
import dev.zuwu.weather.utils.LocationUtils
import dev.zuwu.weather.viewmodels.ForecastViewModel
import dev.zuwu.weather.viewmodels.LocationViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private val locationViewModel: LocationViewModel = LocationViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(locationViewModel)
                }
            }
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyApp(locationViewModel: LocationViewModel) {
    val context = LocalContext.current
    val forecastVM = ForecastViewModel()
    val locUtil = LocationUtils(context, locationViewModel)

    locUtil.getLastKnownLocation()
    val location = locationViewModel.location.value

    runBlocking { forecastVM.getWeatherData("${location?.lat},${location?.lon}") }
    val forecast = forecastVM.weatherData.value

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.height(128.dp).fillMaxWidth()) {
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