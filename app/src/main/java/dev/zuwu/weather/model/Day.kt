package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class Day(

	@field:SerializedName("avgvis_km")
	val avgvisKm: Double,

	@field:SerializedName("uv")
	val uv: Double,

	@field:SerializedName("avgtemp_c")
	val avgtempC: Double,

	@field:SerializedName("daily_chance_of_snow")
	val dailyChanceOfSnow: Int,

	@field:SerializedName("maxtemp_c")
	val maxtempC: Double,

	@field:SerializedName("mintemp_c")
	val mintempC: Double,

	@field:SerializedName("daily_will_it_rain")
	val dailyWillItRain: Int,

	@field:SerializedName("totalsnow_cm")
	val totalsnowCm: Double,

	@field:SerializedName("avghumidity")
	val avghumidity: Int,

	@field:SerializedName("condition")
	val condition: Condition,

	@field:SerializedName("maxwind_kph")
	val maxwindKph: Double,

	@field:SerializedName("daily_chance_of_rain")
	val dailyChanceOfRain: Int,

	@field:SerializedName("totalprecip_mm")
	val totalprecipMm: Double,

	@field:SerializedName("daily_will_it_snow")
	val dailyWillItSnow: Int
)