package dev.zuwu.weather.model

data class Coord(val lat: Double, val lon: Double) {
    override fun toString() = "$lat,$lon"
}