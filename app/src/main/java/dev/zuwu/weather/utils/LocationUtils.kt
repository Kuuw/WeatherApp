package dev.zuwu.weather.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dev.zuwu.weather.views.MainActivity
import dev.zuwu.weather.model.Coord
import dev.zuwu.weather.viewmodels.LocationViewModel

class LocationUtils(val context: Context, val viewModel: LocationViewModel) {
    private val _fusedLocationClient: FusedLocationProviderClient
            = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation(){
        if (hasLocationPermission()) {
            _fusedLocationClient.lastLocation
                .addOnSuccessListener { location->
                    if (location != null) {
                        println("Location: ${location.latitude}, ${location.longitude}")
                        viewModel.updateLocation(Coord(location.latitude, location.longitude))
                    }
                }

        }
        else{
            requestLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates() {
        if (hasLocationPermission()) {
            val locationRequest = com.google.android.gms.location.LocationRequest.Builder(
                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY, 1000L
            ).build()

            val locationCallback = object : com.google.android.gms.location.LocationCallback() {
                override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
                    super.onLocationResult(locationResult)
                    locationResult.lastLocation?.let { location ->
                        viewModel.updateLocation(Coord(location.latitude, location.longitude))
                    }
                }
            }

            _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, android.os.Looper.getMainLooper())
        } else {
            requestLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        if (hasLocationPermission()) {
            val locationRequest = com.google.android.gms.location.LocationRequest.Builder(
                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY, 1000L
            ).build()

            val locationCallback = object : com.google.android.gms.location.LocationCallback() {
                override fun onLocationResult(locationResult: com.google.android.gms.location.LocationResult) {
                    super.onLocationResult(locationResult)
                    locationResult.lastLocation?.let { location ->
                        viewModel.updateLocation(Coord(location.latitude, location.longitude))
                        _fusedLocationClient.removeLocationUpdates(this)
                    }
                }
            }

            _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        } else {
            requestLocationPermission()
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            context as MainActivity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            0
        )
    }

}