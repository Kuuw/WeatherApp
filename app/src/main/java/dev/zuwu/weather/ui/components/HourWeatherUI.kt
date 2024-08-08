package dev.zuwu.weather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.model.Condition
import dev.zuwu.weather.model.HourItem
import dev.zuwu.weather.utils.IconSelector
import dev.zuwu.weather.utils.epochToHour

@Composable
fun HourWeatherUI(hourItem: HourItem?) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(120.dp)
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.align(Alignment.TopCenter)) {
            Text(
                text = epochToHour(hourItem?.timeEpoch?.toLong() ?: 0),
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.align(Alignment.Center)) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .aspectRatio(1f),
                painter = painterResource(
                    id = IconSelector(
                        hourItem?.condition?.code.toString(),
                        hourItem?.isDay ?: 1
                    )
                ),
                contentDescription = ""
            )
        }
        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(
                text = (hourItem?.tempC?.toInt() ?: 0).toString() + "°C",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
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