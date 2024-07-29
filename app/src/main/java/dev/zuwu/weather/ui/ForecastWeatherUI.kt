package dev.zuwu.weather.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.viewmodels.ForecastViewModel
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun ForecastWeatherUI(forecast: ForecastResponse) {
    // Forecast weather UI
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .horizontalScroll(rememberScrollState())
        ) {

            var offset by remember { mutableStateOf(0f) }
            var data = listOf<LineData>()
            val sdf = SimpleDateFormat("E HH:MM")

            for(i in forecast.forecast.forecastday){
                val time = Date(i.dateEpoch.toLong() * 1000)
                data = data + LineData(sdf.format(time), i.day.avgtempC)
            }
            val graphStyle = LineGraphStyle(
                visibility = LineGraphVisibility(
                    isXAxisLabelVisible = true,
                    isYAxisLabelVisible = true,
                    isCrossHairVisible = true
                ),
                colors = LineGraphColors(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.secondary,
                    LineGraphFillType.Gradient(Brush.verticalGradient(listOf(MaterialTheme.colorScheme.surfaceTint, MaterialTheme.colorScheme.background)))
                )
            )

            LineGraph(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxSize()
                    .scrollable(
                        orientation = Orientation.Horizontal,
                        state = rememberScrollableState { delta ->
                            offset = offset.plus(delta)
                            delta
                        }
                    )
                    .width((forecast.forecast.forecastday.size * 80).dp),
                data = data,
                onPointClick = {},
                style = graphStyle
            )

            val scrollState = rememberVicoScrollState()
            val zoomState = rememberVicoZoomState()
            val model = CartesianChartModelProducer


        }
    }
}


@Composable
@Preview(showBackground = true)
fun ForecastWeatherUIPreview() {
    // Preview ForecastWeatherUI
    val forecastVM = ForecastViewModel()

    val forecast = runBlocking {
        forecastVM.getWeatherData("Istanbul")
        while (forecastVM.weatherData.value == null) {
            println("Waiting for response...")
            Thread.sleep(250)
        }
        forecastVM.weatherData.value!!
    }

    ForecastWeatherUI(forecast = forecast)
}