package dev.zuwu.weather.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.zuwu.weather.R
import me.zhanghai.compose.preference.ListPreferenceType
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.defaultPreferenceFlow
import me.zhanghai.compose.preference.listPreference

@Composable
fun SettingsScreen(navBack: () -> Unit) {
    ProvidePreferenceLocals(flow = defaultPreferenceFlow()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text("Ayarlar", modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart), style = MaterialTheme.typography.headlineMedium)
                    Button(
                        onClick = { navBack() },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(Icons.Default.Done, contentDescription = "")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Geri")
                    }
                }
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                listPreference(
                    "UNIT_PREFERENCE",
                    "metric",
                    listOf("metric", "imperial"),
                    { Text("Birim Tercihi") },
                    type = ListPreferenceType.DROPDOWN_MENU,
                    summary = { val text = when (it) {
                        "metric" -> AnnotatedString("Metrik")
                        "imperial" -> AnnotatedString("Emperal")
                        else -> AnnotatedString("Hata")
                    }
                              Text(text)
                              },
                    icon = { Image(painter = painterResource(id = R.drawable.thermometer), contentDescription = "")},
                    valueToText = {
                        when (it) {
                            "metric" -> AnnotatedString("Metrik")
                            "imperial" -> AnnotatedString("Emperal")
                            else -> AnnotatedString("Hata")
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterHorizontally), contentAlignment = Alignment.Center) {
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append("Developed by ")
                    }
                    pushStringAnnotation(tag = "kuuw", annotation = "https://github.com/kuuw")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append("Ata Karadağ - ")
                    }
                    pop()
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                        append("Icons made by ")
                    }
                    pushStringAnnotation(tag = "bas", annotation = "https://github.com/basmilius/weather-icons")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append("Bas Milius")
                    }
                    pop()
                }
                val uriHandler = LocalUriHandler.current
                ClickableText(text = annotatedString, style = MaterialTheme.typography.bodySmall, onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "bas", start = offset, end = offset).firstOrNull()?.let {
                        uriHandler.openUri(it.item)
                        Log.d("bas URL", it.item)
                    }

                    annotatedString.getStringAnnotations(tag = "kuuw", start = offset, end = offset).firstOrNull()?.let {
                        uriHandler.openUri(it.item)
                        Log.d("kuuw URL", it.item)
                    }
                },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                )
            }
        }
    }
}