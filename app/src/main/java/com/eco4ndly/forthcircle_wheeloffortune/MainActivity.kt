package com.eco4ndly.forthcircle_wheeloffortune

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.TextViewCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_surprise_reveal.view.*
import kotlinx.android.synthetic.main.text_input.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import rubikstudio.library.model.LuckyItem

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainScope.launch {
            TextViewCompat.setAutoSizeTextTypeWithDefaults(tv_txt, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
            setUpLuckyWheel()
            btn_spin.visibility = View.INVISIBLE
            luckyWheel.visibility = View.INVISIBLE
            tv_txt.visibility = View.INVISIBLE

            for (i in 1..100 step 10) {
                main_progress.progress = i
                if (i > 30) {
                    tv_txt.visibility = View.VISIBLE
                }
                if (i > 70) {
                    luckyWheel.visibility = View.VISIBLE
                }
                if (i > 90) {
                    btn_spin.visibility = View.VISIBLE
                }
                delay(500)
            }

            btn_spin.visibility = View.VISIBLE
            luckyWheel.visibility = View.VISIBLE
            tv_txt.visibility = View.VISIBLE
            main_progress.visibility = View.GONE


            btn_spin.setOnClickListener {
                luckyWheel.startLuckyWheelWithRandomTarget()
            }

            tv_txt.setOnClickListener {
                AlertDialog.Builder(this@MainActivity).apply {
                    val view =
                        LayoutInflater.from(this@MainActivity).inflate(R.layout.text_input, null, false)
                    setView(view)
                    setCancelable(true)
                    setPositiveButton("Save") { _, _ ->
                        val text = view.input.text.toString()
                        if (!text.isNullOrEmpty()) {
                            SharedPre.sharedPreferences.edit {
                                putString(SharedPre.SURPRISE, text)
                            }
                        }
                    }
                    show()
                }
            }
        }
    }

    private fun setUpLuckyWheel() {
        val luckyItems = mutableListOf<LuckyItem>()
        ConstVal.items.withIndex().forEach {
            val luckyItem = LuckyItem()
            luckyItem.topText = it.value
            luckyItem.secondaryText = it.index.toString()
            //luckyItem.color = 0xffFFF3E0.toInt()
            if (it.index % 2 == 0) {
                luckyItem.color = 0xffffd54f.toInt()
            } else {
                luckyItem.color = 0xffffe57f.toInt()
            }
            luckyItems.add(luckyItem)
        }

        luckyWheel.setData(luckyItems)
        luckyWheel.setRound(4)

        luckyWheel.setLuckyRoundItemSelectedListener {
            Handler().postDelayed({
                val text = "You won ${ConstVal.items[it]}"
                Bundle().apply {
                    putString(SurpriseRevealDialogFragment.SURPRISE, text)
                    val dialog = SurpriseRevealDialogFragment()
                    dialog.arguments = this
                    dialog.setOnDialogCloseListener(object : DialogFragmentDismissListener {
                        override fun onClose(closeApp: Boolean) {
                            if (closeApp) finish()
                        }

                    })
                    dialog.show(supportFragmentManager, "SURPRISE DIALOG")
                }
            }, 500)
        }
    }
}
