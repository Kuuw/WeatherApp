package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class Current(

	@field:SerializedName("feelslike_c")
	val feelslikeC: Double,

	@field:SerializedName("uv")
	val uv: Double,

	@field:SerializedName("last_updated")
	val lastUpdated: String,

	@field:SerializedName("wind_degree")
	val windDegree: Int,

	@field:SerializedName("dewpoint_c")
	val dewpointC: Double,

	@field:SerializedName("windchill_c")
	val windchillC: Double,

	@field:SerializedName("last_updated_epoch")
	val lastUpdatedEpoch: Int,

	@field:SerializedName("is_day")
	val isDay: Int,

	@field:SerializedName("heatindex_c")
	val heatindexC: Double,

	@field:SerializedName("wind_dir")
	val windDir: String,

	@field:SerializedName("temp_c")
	val tempC: Double,

	@field:SerializedName("gust_kph")
	val gustKph: Double,

	@field:SerializedName("precip_mm")
	val precipMm: Double,

	@field:SerializedName("cloud")
	val cloud: Int,

	@field:SerializedName("wind_kph")
	val windKph: Double,

	@field:SerializedName("condition")
	val condition: Condition,

	@field:SerializedName("vis_km")
	val visKm: Double,

	@field:SerializedName("humidity")
	val humidity: Int,

	@field:SerializedName("pressure_mb")
	val pressureMb: Double
)