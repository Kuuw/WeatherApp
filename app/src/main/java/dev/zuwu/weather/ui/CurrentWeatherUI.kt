package dev.zuwu.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.R
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.utils.IconSelector

@Composable
fun CurrentWeatherUI(forecast: ForecastResponse?) {
    println(forecast?.current?.condition?.icon)
    // Current weather UI
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top=16.dp, start=16.dp, end=16.dp)
            .height(96.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)) {
            Card(modifier = Modifier.padding(8.dp)) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp)
                        .aspectRatio(1f),
                    painter = painterResource(id = IconSelector(forecast?.current?.condition?.code.toString(), forecast?.current?.isDay ?: 1)),
                    contentDescription = ""
                )
            }
            Column(modifier = Modifier.fillMaxHeight()) {
                Spacer(modifier = Modifier.size(4.dp))
                Row{
                    Text(
                        forecast?.location?.name.toString(),
                        modifier = Modifier
                            .padding(4.dp)
                            .align(alignment = Alignment.Top),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)) {
                        Text(
                            forecast?.current?.tempC?.toInt().toString() + "°C",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .align(alignment = Alignment.TopEnd),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
                Box {
                    Text(
                        camelCase(forecast?.current?.condition?.text.toString()),
                        modifier = Modifier
                            .padding(bottom = 4.dp, start= 4.dp)
                            .align(alignment = Alignment.TopStart),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row{
                    Box(modifier = Modifier.width(56.dp)) {
                        Icon(
                            Icons.Rounded.KeyboardArrowUp,
                            "",
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                        Text(
                            forecast?.forecast?.forecastday?.get(0)?.day?.maxtempC?.toInt().toString() + "°C",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .padding(4.dp)
                                .align(alignment = Alignment.CenterEnd)
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Box(modifier = Modifier.width(56.dp)) {
                        Icon(
                            Icons.Rounded.KeyboardArrowDown,
                            "",
                            tint = MaterialTheme.colorScheme.surfaceTint,
                            modifier = Modifier.align(alignment = Alignment.CenterStart)
                        )
                        Text(
                            forecast?.forecast?.forecastday?.get(0)?.day?.mintempC?.toInt().toString() + "°C",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .padding(4.dp)
                                .align(alignment = Alignment.CenterEnd)
                        )
                    }
                    Spacer(modifier = Modifier.size(25.dp))
                    Box(modifier = Modifier.width(50.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.rain_icon), "",
                            tint = MaterialTheme.colorScheme.surfaceTint,
                            modifier = Modifier.size(14.dp).align(Alignment.CenterStart)
                        )
                        Text(
                            forecast?.forecast?.forecastday?.get(0)?.day?.dailyChanceOfRain?.toString() + "%",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .padding(4.dp)
                                .align(alignment = Alignment.CenterEnd)
                        )
                    }
                }
            }
        }
    }
}

fun camelCase(string: String, delimiter: String = " ", separator: String = " "): String {
    return string.split(delimiter).joinToString(separator = separator) {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }
}