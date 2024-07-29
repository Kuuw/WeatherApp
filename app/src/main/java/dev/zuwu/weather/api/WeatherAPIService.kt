package dev.zuwu.weather.api

import dev.zuwu.weather.model.ForecastResponse

interface WeatherAPIService {
    suspend fun fetchWeatherData(q: String): ForecastResponse
}