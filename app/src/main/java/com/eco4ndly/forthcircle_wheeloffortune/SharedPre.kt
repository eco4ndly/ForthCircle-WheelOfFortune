package com.eco4ndly.forthcircle_wheeloffortune

import android.content.Context
import android.content.SharedPreferences

/**
 * A Sayan Porya code on 17/05/20
 */
object SharedPre {
    val sharedPreferences: SharedPreferences = MyApp.appInstance.getSharedPreferences("com.eco4ndly.forthcircle_wheeloffortune", Context.MODE_PRIVATE)


    const val SURPRISE = "surprise"
    const val SHOW_CLICK_HERE = "show_click_here"
}