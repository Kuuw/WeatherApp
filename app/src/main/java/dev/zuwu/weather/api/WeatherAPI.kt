package dev.zuwu.weather.api

import dev.zuwu.weather.model.ForecastResponse
import dev.zuwu.weather.model.SearchResponseItem
import dev.zuwu.weather.utils.Utils
import dev.zuwu.weather.utils.fetchRawJson
import dev.zuwu.weather.utils.parseJsonToForecast
import dev.zuwu.weather.utils.parseJsonToSearch
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class WeatherAPI: WeatherAPIService {
    val urlBuilder = URLBuilder().apply {
        protocol = URLProtocol.HTTPS
        host = Utils.BASE_URL
        encodedPath = "/v1/forecast.json"
        parameters.append("key", Utils.API_KEY)
        parameters.append("lang", Utils.LANG)
    }

    override suspend fun fetchWeatherData(q: String): ForecastResponse {
        return suspendCancellableCoroutine { continuation ->
            val url = urlBuilder.apply {
                parameters.append("q", q)
                parameters.append("days", Utils.DAYS)
            }.buildString()
            println(url)
            fetchRawJson(url) { json ->
                if (json != null) {
                    val forecastResponse = parseJsonToForecast(json)
                    continuation.resume(forecastResponse)
                } else {
                    continuation.resumeWithException(IOException("Failed to fetch data"))
                }
            }
        }
    }

    override suspend fun fetchSearchData(q: String): List<SearchResponseItem> {
        return suspendCancellableCoroutine { continuation ->
            val url = urlBuilder.apply {
                encodedPath = "/v1/search.json"
                parameters.append("q", q)
            }.buildString()
            println(url)
            if(q!=""){
                fetchRawJson(url) { json ->
                    if (json != null) {
                        val searchResponse = parseJsonToSearch(json)
                        continuation.resume(searchResponse)
                    } else {
                        continuation.resumeWithException(IOException("Failed to fetch data"))
                    }
                }
            } else {
                continuation.resume(emptyList())
            }
        }
    }
}