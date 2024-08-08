package dev.zuwu.weather.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.ui.components.HourWeatherUI
import dev.zuwu.weather.ui.components.rotateVertically
import java.util.Calendar

@Composable
fun TodayWeatherUI(forecast: ForecastResponse?) {
    var offset by remember { mutableStateOf(0f) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .height(96.dp)
    ) {
        Row {
            Text(
                text = "24 Saat",
                modifier = Modifier
                    .rotateVertically(clockwise = false)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .scrollable(
                        orientation = Orientation.Horizontal,
                        state = rememberScrollableState { delta ->
                            offset = offset.plus(delta)
                            delta
                        }
                    )
            ) {

                forecast?.forecast?.forecastday.let { forecastday ->
                    val calendar = Calendar.getInstance()
                    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
                    items(forecastday?.get(0)?.hour?.size!!) { forecastInt ->
                        if(forecastInt > currentHour) {
                            HourWeatherUI(forecast?.forecast?.forecastday?.get(0)?.hour?.get(forecastInt))
                        }
                    }
                    items(forecastday?.get(1)?.hour?.size!!) { forecastInt ->
                        if(forecastInt < currentHour+1) {
                            HourWeatherUI(forecast?.forecast?.forecastday?.get(1)?.hour?.get(forecastInt))
                        }
                    }
                }
            }
            Icon(
                Icons.Rounded.KeyboardArrowUp,
                "",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(alignment = Alignment.CenterVertically).rotateVertically().padding(8.dp)
            )
        }
    }
}