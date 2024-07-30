package dev.zuwu.weather.utils

import dev.zuwu.weather.R

fun IconSelector(code: String?, isDay: Int): Int {
    return when (code) {
        "1000" -> if (isDay == 1) { R.drawable.clear } else { R.drawable.clear_night }
        "1003" -> if (isDay == 1) { R.drawable.partly_cloudy_day } else { R.drawable.partly_cloudy_night }
        "1006" -> R.drawable.cloudy
        "1009" -> R.drawable.overcast
        "1030" -> R.drawable.mist
        "1063" -> if(isDay == 1) {R.drawable.partly_cloudy_day_rain} else {R.drawable.partly_cloudy_night_rain}
        "1066" -> if(isDay == 1) {R.drawable.partly_cloudy_day_snow} else {R.drawable.partly_cloudy_night_snow}
        "1069" -> if(isDay == 1) {R.drawable.partly_cloudy_day_sleet} else {R.drawable.partly_cloudy_night_sleet}
        "1072" -> if(isDay == 1) {R.drawable.partly_cloudy_day_drizzle} else {R.drawable.partly_cloudy_night_drizzle}
        "1087" -> R.drawable.thunderstorms
        "1114" -> R.drawable.snow
        "1117" -> R.drawable.snowy
        "1135" -> R.drawable.fog
        "1147" -> R.drawable.fog
        "1150" -> if(isDay == 1) {R.drawable.partly_cloudy_day_drizzle} else {R.drawable.partly_cloudy_night_drizzle}
        "1153" -> if(isDay == 1) {R.drawable.partly_cloudy_day_drizzle} else {R.drawable.partly_cloudy_night_drizzle}
        "1168" -> if(isDay == 1) {R.drawable.partly_cloudy_day_drizzle} else {R.drawable.partly_cloudy_night_drizzle}
        "1171" -> if(isDay == 1) {R.drawable.partly_cloudy_day_drizzle} else {R.drawable.partly_cloudy_night_drizzle}
        "1180" -> if(isDay == 1) {R.drawable.partly_cloudy_day_rain} else {R.drawable.partly_cloudy_night_rain}
        "1183" -> if(isDay == 1) {R.drawable.partly_cloudy_day_rain} else {R.drawable.partly_cloudy_night_rain}
        "1186" -> R.drawable.rainy
        "1189" -> R.drawable.rainy
        "1192" -> R.drawable.rainy
        "1195" -> R.drawable.rainy
        "1198" -> R.drawable.rainy
        "1201" -> R.drawable.rainy
        "1204" -> R.drawable.sleet
        "1207" -> R.drawable.sleet
        "1210" -> R.drawable.snow
        "1213" -> R.drawable.snow
        "1216" -> R.drawable.snowy
        "1219" -> R.drawable.snowy
        "1222" -> R.drawable.snowy
        "1225" -> R.drawable.snowy
        "1237" -> R.drawable.snow
        "1240" -> R.drawable.rainy
        "1243" -> R.drawable.rainy
        "1246" -> R.drawable.rainy
        "1249" -> R.drawable.sleet
        "1252" -> R.drawable.sleet
        "1255" -> R.drawable.snow
        "1258" -> R.drawable.snowy
        "1261" -> R.drawable.snow
        "1264" -> R.drawable.snow
        "1273" -> if(isDay == 1) {R.drawable.thunderstorms_day_rain} else {R.drawable.thunderstorms_night_rain}
        "1276" -> R.drawable.thunderstorms_rain
        "1279" -> if(isDay == 1) {R.drawable.partly_cloudy_day_snow} else {R.drawable.partly_cloudy_night_snow}
        "1282" -> R.drawable.snowy
        else -> if(isDay == 1) { R.drawable.clear } else { R.drawable.clear_night }
    }
}
