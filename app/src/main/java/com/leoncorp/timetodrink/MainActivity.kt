package com.leoncorp.timetodrink

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextClock
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var drunk: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("drunk", drunk)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        drunk = savedInstanceState.getBoolean("drinked",false)
        if(drunk){
            alreadyDrink()
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun alreadyDrink(){
        val textClock = findViewById<TextClock>(R.id.textClock)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        var sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        sdf = SimpleDateFormat("u")
        when (sdf.format(d).toInt()) {
            in arrayListOf<Int>(1, 2, 4) -> {
                textClock.format24Hour = "Ура! Пивной $dayOfTheWeek"
                textClock.format12Hour = "Ура! Пивной $dayOfTheWeek"
            }
            in arrayListOf<Int>(3, 5, 6) -> {
                textClock.format24Hour = "Ура! Пивная $dayOfTheWeek"
                textClock.format12Hour = "Ура! Пивная $dayOfTheWeek"
            }
            7 -> {
                textClock.format24Hour = "Ура! Пивное $dayOfTheWeek"
                textClock.format12Hour = "Ура! Пивное $dayOfTheWeek"
            }
        }
        drunk = true
        imageView.visibility = View.VISIBLE
        button.visibility = View.INVISIBLE

    }

    fun buttonDrink(view: View){
        alreadyDrink()
    }


}