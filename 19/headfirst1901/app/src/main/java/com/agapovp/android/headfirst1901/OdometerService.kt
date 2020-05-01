package com.agapovp.android.headfirst1901

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat

class OdometerService : Service() {

    private val binder: IBinder = OdometerBinder()

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager

    override fun onCreate() {
        super.onCreate()

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (lastLocation == null) {
                    lastLocation = location
                }
                distanceInMeters += location.distanceTo(lastLocation)
                lastLocation = location
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) = Unit

            override fun onProviderEnabled(provider: String?) = Unit

            override fun onProviderDisabled(provider: String?) = Unit
        }

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ContextCompat.checkSelfPermission(this, PERMISSION_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.getBestProvider(Criteria(), true)?.let {
                locationManager.requestLocationUpdates(it, 1000, 1f, locationListener)
            }
        }
    }

    override fun onBind(intent: Intent) = binder

    override fun onDestroy() {
        super.onDestroy()

        if (ContextCompat.checkSelfPermission(this, PERMISSION_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.removeUpdates(locationListener)
        }
    }

    fun getDistance() = distanceInMeters / resources.getFloat(R.dimen.divider)

    inner class OdometerBinder : Binder() {
        fun getOdometer() = this@OdometerService
    }

    companion object {
        const val PERMISSION_FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION

        private var lastLocation: Location? = null
        private var distanceInMeters = 0.0
    }
}
