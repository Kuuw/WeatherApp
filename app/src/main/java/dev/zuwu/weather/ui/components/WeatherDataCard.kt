package dev.zuwu.weather.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    ) {
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