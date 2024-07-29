package dev.zuwu.weather.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.zuwu.weather.model.Coord

class LocationViewModel: ViewModel() {
    private val _location = mutableStateOf<Coord?>(null)
    val location: State<Coord?> = _location

    fun updateLocation(newLocation: Coord){
        _location.value = newLocation
    }
}