package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

	@field:SerializedName("current")
	val current: Current,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("forecast")
	val forecast: Forecast
)