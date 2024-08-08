package dev.zuwu.weather.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("SearchResponse")
	val searchResponse: List<SearchResponseItem>
)

data class SearchResponseItem(

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("region")
	val region: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("url")
	val url: String
)
