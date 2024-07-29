package dev.zuwu.weather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.zuwu.weather.ui.CurrentWeatherUI
import dev.zuwu.weather.ui.theme.MaterialWeatherTheme
import dev.zuwu.weather.viewmodels.ForecastViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyApp(){
    val context = LocalContext.current
    val forecastVM = ForecastViewModel()

    val forecast = runBlocking {
        forecastVM.getWeatherData("Istanbul")
        while (forecastVM.weatherData.value == null) {
            println("Waiting for response...")
            Thread.sleep(250)
        }
        forecastVM.weatherData.value!!
    }

    Row(modifier = Modifier.fillMaxSize()) {
        CurrentWeatherUI(forecast = forecast)
        Spacer(modifier = Modifier.fillMaxSize())
    }
}