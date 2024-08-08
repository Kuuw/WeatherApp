package dev.zuwu.weather.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.zuwu.weather.ui.theme.MaterialWeatherTheme
import dev.zuwu.weather.utils.LocationUtils
import dev.zuwu.weather.utils.PreferencesManager
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
    val navController = rememberNavController()

    val context = LocalContext.current
    val forecastVM = ForecastViewModel()
    val locUtil = LocationUtils(context, locationViewModel)

    locUtil.getLastKnownLocation()
    val location = locationViewModel.location.value
    runBlocking { forecastVM.getWeatherData("${location?.lat},${location?.lon}") }

    val PreferencesManager = PreferencesManager(context)
    Log.d("Unit Preference: ", PreferencesManager.getData("UNIT_PREFERENCE", "Celcius"))

    NavHost(navController = navController, startDestination = "MainWeatherScreen") {
        composable("MainWeatherScreen") {
            MainWeatherScreen(locUtil, forecastVM, locationViewModel,
                { navController.navigate("SettingsScreen") }
            )
        }
        composable("SettingsScreen") {
            SettingsScreen({ navController.navigate("MainWeatherScreen") })
        }
    }
}