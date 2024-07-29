package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class Astro(

	@field:SerializedName("moonset")
	val moonset: String,

	@field:SerializedName("moon_illumination")
	val moonIllumination: Int,

	@field:SerializedName("sunrise")
	val sunrise: String,

	@field:SerializedName("moon_phase")
	val moonPhase: String,

	@field:SerializedName("sunset")
	val sunset: String,

	@field:SerializedName("is_moon_up")
	val isMoonUp: Int,

	@field:SerializedName("is_sun_up")
	val isSunUp: Int,

	@field:SerializedName("moonrise")
	val moonrise: String
)