package com.eco4ndly.forthcircle_wheeloffortune

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

/**
 * A Sayan Porya code on 22/05/20
 */

/**
 * Extension function to do shared pref stuffs in a DSLish way
 *
 * ````` Usage ``````
        preferences.edit {
            putString(TOKEN, tokenValue)
        }
 * ```````````````````
 */
@SuppressLint("ApplySharedPref")
inline fun SharedPreferences.edit(commit: Boolean = false, action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    if (commit) editor.commit()
    else editor.apply()
}

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()

typealias ProjectColor = R.color