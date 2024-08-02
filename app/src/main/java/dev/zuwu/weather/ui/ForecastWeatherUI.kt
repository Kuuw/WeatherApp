package dev.zuwu.weather.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.zuwu.weather.R
import dev.zuwu.weather.model.Forecast
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.utils.epochToDay

@SuppressLint("SimpleDateFormat")
@Composable
fun ForecastWeatherUI(forecast: ForecastResponse?) {
    // Forecast weather UI
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(256.dp)
    ) {
        Column(modifier = Modifier) {
            val selectedOption = remember { mutableIntStateOf(1) }
            val forecastDay = forecast?.forecast?.forecastday?.get(selectedOption.value)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .align(Alignment.CenterHorizontally),
            ) {
                CustomRadioGroup(forecast?.forecast, selectedOption)
            }
            Row {
                Spacer(modifier = Modifier.size(10.dp))
                WeatherDataCard(
                    icon = R.drawable.thermometer_colder,
                    title = "En Düşük Sıcaklık",
                    value = forecastDay?.day?.mintempC?.toInt().toString(),
                    unit = "°C"
                )
                Spacer(modifier = Modifier.size(7.dp))
                WeatherDataCard(
                    icon = R.drawable.thermometer_mercury,
                    title = "Ortalama Sıcaklık",
                    value = forecastDay?.day?.avgtempC?.toInt().toString(),
                    unit = "°C"
                )
                Spacer(modifier = Modifier.size(7.dp))
                WeatherDataCard(
                    icon = R.drawable.thermometer_warmer,
                    title = "En Yüksek Sıcaklık",
                    value = forecastDay?.day?.maxtempC?.toInt().toString(),
                    unit = "°C"
                )
                Spacer(modifier = Modifier.size(7.dp))
                WeatherDataCard(
                    icon = R.drawable.humidity,
                    title = "Nem Oranı",
                    value = forecastDay?.day?.avghumidity?.toInt().toString(),
                    unit = "%"
                )
            }
            Row {
                Spacer(modifier = Modifier.size(10.dp))
                WeatherDataCard(
                    icon = R.drawable.raindrops,
                    title = "Yağmur İhtimali",
                    value = forecastDay?.day?.dailyChanceOfRain.toString(),
                    unit = "%"
                )
                Spacer(modifier = Modifier.size(7.dp))
                WeatherDataCard(
                    icon = R.drawable.windsock,
                    title = "Rüzgar Hızı",
                    value = forecastDay?.day?.maxwindKph?.toInt().toString(),
                    unit = "km/h"
                )
                Spacer(modifier = Modifier.size(7.dp))
                WeatherDataCard(
                    icon = R.drawable.uv_index,
                    title = "UV İndeksi",
                    value = forecastDay?.day?.uv?.toInt().toString(),
                    unit = "/11"
                )
                Spacer(modifier = Modifier.size(7.dp))
                WeatherDataCard(
                    icon = R.drawable.mist,
                    title = "Görüş Mesafesi",
                    value = forecastDay?.day?.avgvisKm?.toInt().toString(),
                    unit = "%"
                )
            }
        }
    }
}


@Composable
fun CustomRadioGroup(forecastDays: Forecast?, selectedOption: MutableState<Int>) {
    var optionNames = mutableListOf<String>("Bugün", "Yarın")
    for(i in 2 until forecastDays?.forecastday?.size!!){
        optionNames.add(epochToDay(forecastDays.forecastday[i].dateEpoch.toLong()))
    }

    val onSelectionChange = { text: String ->
        selectedOption.value = optionNames.indexOf(text)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
        modifier = Modifier
            .padding(all = 8.dp)
    ) { Spacer(modifier = Modifier.size(8.dp))
        optionNames.forEach { text ->
                Text(
                    text = text,
                    style = typography.bodyMedium.merge(),
                    color = if (optionNames.indexOf(text) == selectedOption.value) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.tertiary
                    },
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                        .clickable { onSelectionChange(text) }
                        .padding(
                            vertical = 8.dp,
                            horizontal = 4.dp,
                        )
                )
            }
        }
    }
}

@Composable
fun WeatherDataCard(icon: Int, title: String, value: String, unit: String) {
    Box(modifier = Modifier.height(95.dp)) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.TopStart)
                .zIndex(1f)
                .padding(start = 0.dp)
                .aspectRatio(.5f),
            contentScale = ContentScale.Crop,

        )
        Card(
            modifier = Modifier
                .size(85.dp)
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
            // Weather data card
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)) {
                        Text(
                            text = title,
                            style = TextStyle(fontSize = 9.sp, textAlign = TextAlign.Center),
                            modifier = Modifier
                                .padding(2.dp)
                                .align(Alignment.Center)
                        )
                    }

                }
                Spacer(modifier = Modifier.size(8.dp))
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = value,
                            style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center),
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .align(Alignment.Bottom)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Text(
                            text = unit,
                            style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center),
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .align(Alignment.Bottom)
                                .padding(bottom = 2.dp)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ForecastWeatherUIPreview() {
    WeatherDataCard(icon = R.drawable.thermometer_colder, title = "En Düşük Sıcaklık", value = "15", unit = "°C")
}