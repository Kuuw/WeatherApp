package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class ForecastdayItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("astro")
	val astro: Astro,

	@field:SerializedName("date_epoch")
	val dateEpoch: Int,

	@field:SerializedName("hour")
	val hour: List<HourItem>,

	@field:SerializedName("day")
	val day: Day
)