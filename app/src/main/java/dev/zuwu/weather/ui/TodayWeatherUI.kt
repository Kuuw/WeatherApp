package dev.zuwu.weather.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.model.Condition
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.model.HourItem
import dev.zuwu.weather.utils.IconSelector
import java.text.SimpleDateFormat
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

@Composable
fun HourWeatherUI(hourItem: HourItem?) {
    Box(modifier = Modifier
        .width(80.dp)
        .height(120.dp)
        .padding(8.dp)
        ) {
        Row(modifier = Modifier.align(Alignment.TopCenter)) {
            Text(text = epochToHour(hourItem?.timeEpoch?.toLong() ?: 0),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium)
        }
        Row(modifier = Modifier.align(Alignment.Center)) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = IconSelector(hourItem?.condition?.code.toString(), hourItem?.isDay ?: 1)),
                contentDescription = ""
            )
        }
        Row(modifier = Modifier.align(Alignment.BottomCenter)){
            Text(text = (hourItem?.tempC?.toInt() ?: 0).toString() + "°C",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun epochToHour(epoch: Long): String {
    val sdf = SimpleDateFormat("HH:00")
    return sdf.format(epoch * 1000)
}

@Preview(showBackground = true)
@Composable
fun PreviewHourWeatherUI() {
    HourWeatherUI(hourItem =
        HourItem(
            willItRain = 0,
            feelslikeC = 25.0,
            timeEpoch = 1633669200,
            windDegree = 0,
            dewpointC = 0.0,
            windchillC = 25.0,
            isDay = 1,
            heatindexC = 25.0,
            windDir = "N",
            tempC = 25.0,
            chanceOfRain = 0,
            precipMm = 0.0,
            cloud = 0,
            windKph = 0.0,
            condition = Condition(
                text = "Partly cloudy",
                icon = "//cdn.weatherapi.com/weather/64x64/night/116.png",
                code = 1003
            ),
            snowCm = 0.0,
            willItSnow = 0,
            visKm = 10.0,
            chanceOfSnow = 0,
            humidity = 0,
            time = "2021-10-08 00:00",
            pressureMb = 1015.0
        )
    )
}

fun Modifier.rotateVertically(clockwise: Boolean = true): Modifier {
    val rotate = rotate(if (clockwise) 90f else -90f)

    val adjustBounds = layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }
    return rotate then adjustBounds
}