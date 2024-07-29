package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class Forecast(

	@field:SerializedName("forecastday")
	val forecastday: List<ForecastdayItem>
)