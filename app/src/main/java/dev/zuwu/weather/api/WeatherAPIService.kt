package dev.zuwu.weather.api

import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.model.SearchResponseItem

interface WeatherAPIService {
    suspend fun fetchWeatherData(q: String): ForecastResponse

    suspend fun fetchSearchData(q: String): List<SearchResponseItem>
}