package com.app.debugmyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class StudyBuildApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}