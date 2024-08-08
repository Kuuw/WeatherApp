package dev.zuwu.weather.utils

fun Double.toFahrenheit(): Double {
    return this * 9 / 5 + 32
}

fun Double.toMiles(): Double {
    return this * 0.621371
}