package com.nu.dasmarinas.facilities

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FacilitiesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
