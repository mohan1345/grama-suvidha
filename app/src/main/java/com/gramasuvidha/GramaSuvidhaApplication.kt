package com.gramasuvidha

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GramaSuvidhaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
