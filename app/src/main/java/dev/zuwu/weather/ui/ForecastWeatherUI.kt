package dev.zuwu.weather.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.R
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.ui.components.CustomRadioGroup
import dev.zuwu.weather.ui.components.WeatherDataCard

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
                    icon = R.drawable.thermometer,
                    title = "Ortalama Sıcaklık",
                    value = forecastDay?.hour?.map { it.tempC }?.average()?.toInt().toString(),
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
                    unit = "km"
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ForecastWeatherUIPreview() {
    WeatherDataCard(icon = R.drawable.thermometer_colder, title = "En Düşük Sıcaklık", value = "15", unit = "°C")
}