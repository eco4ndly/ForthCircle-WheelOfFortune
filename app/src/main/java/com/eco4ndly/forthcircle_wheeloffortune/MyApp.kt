package com.eco4ndly.forthcircle_wheeloffortune

import android.app.Application

/**
 * A Sayan Porya code on 17/05/20
 */
class MyApp: Application() {
    companion object {
        lateinit var appInstance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}