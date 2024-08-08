package dev.zuwu.weather.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.model.Forecast
import dev.zuwu.weather.utils.epochToDay

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
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            optionNames.forEach { text ->
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.merge(),
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