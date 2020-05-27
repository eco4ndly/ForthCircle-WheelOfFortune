package com.eco4ndly.forthcircle_wheeloffortune

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_opening_screen.*

class OpeningScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_screen)

        val showClickHere = SharedPre.sharedPreferences.getBoolean(SharedPre.SHOW_CLICK_HERE, true)

        tv_change_your_luck.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            Handler().postDelayed(Runnable {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }, 500)
        }

        tv_already_changed.setOnLongClickListener {
            SharedPre.sharedPreferences.edit {
                putBoolean(SharedPre.SHOW_CLICK_HERE, true).apply()
                toast("Reset Successful")
                tv_change_your_luck.visibility = View.VISIBLE
                tv_already_changed.visibility = View.GONE
            }
            false
        }

        if (showClickHere) {
            tv_change_your_luck.visibility = View.VISIBLE
            tv_already_changed.visibility = View.GONE
        } else {
            tv_change_your_luck.visibility = View.GONE
            tv_already_changed.visibility = View.VISIBLE
        }
    }
}
