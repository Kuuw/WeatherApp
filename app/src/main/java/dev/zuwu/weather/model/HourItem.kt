package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class HourItem(

	@field:SerializedName("will_it_rain")
	val willItRain: Int,

	@field:SerializedName("feelslike_c")
	val feelslikeC: Double,

	@field:SerializedName("wind_degree")
	val windDegree: Int,

	@field:SerializedName("dewpoint_c")
	val dewpointC: Double,

	@field:SerializedName("windchill_c")
	val windchillC: Double,

	@field:SerializedName("is_day")
	val isDay: Int,

	@field:SerializedName("heatindex_c")
	val heatindexC: Double,

	@field:SerializedName("wind_dir")
	val windDir: String,

	@field:SerializedName("temp_c")
	val tempC: Double,

	@field:SerializedName("chance_of_rain")
	val chanceOfRain: Int,

	@field:SerializedName("precip_mm")
	val precipMm: Double,

	@field:SerializedName("cloud")
	val cloud: Int,

	@field:SerializedName("wind_kph")
	val windKph: Double,

	@field:SerializedName("condition")
	val condition: Condition,

	@field:SerializedName("snow_cm")
	val snowCm: Double,

	@field:SerializedName("will_it_snow")
	val willItSnow: Int,

	@field:SerializedName("vis_km")
	val visKm: Double,

	@field:SerializedName("time_epoch")
	val timeEpoch: Int,

	@field:SerializedName("humidity")
	val humidity: Int,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("chance_of_snow")
	val chanceOfSnow: Int,

	@field:SerializedName("pressure_mb")
	val pressureMb: Double
)