package dev.zuwu.weather.utils


import com.google.gson.Gson
import dev.zuwu.weather.model.ForecastResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

fun parseJson(jsonString: String): ForecastResponse {
    val gson = Gson()
    return gson.fromJson(jsonString, ForecastResponse::class.java)
}

fun fetchRawJson(url: String, callback: (String?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string()
            callback(responseBody)
        } catch (e: IOException) {
            e.printStackTrace()
            callback(null)
        }
    }
}

suspend fun fetchForecast(url: String): ForecastResponse? {
    return withContext(Dispatchers.IO) {


        var forecastResponse: ForecastResponse? = null
        fetchRawJson(url) { json ->
            json?.let {
                println("\n\n")
                println(it)
                println("\n\n")
                forecastResponse = parseJson(it)
            }
        }
        // Wait for the fetchRawJson function to complete
        while (forecastResponse == null) {
            println("Waiting for response...")
            Thread.sleep(250)
        }
        println("\n\n")
        println(forecastResponse?.location?.name)
        println("\n\n")

        forecastResponse
    }
}