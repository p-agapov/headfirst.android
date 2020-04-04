package com.agapovp.android.headfirst0501

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_layout_examples.*

class LayoutExamplesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_examples)
    }

    fun onClickSend(view: View) {
        startActivity(Intent(this, DuckActivity::class.java))
    }

    fun onClickToggle(view: View) {
        if (toggle.isChecked) {
            message.hint = "Phone"
        } else {
            message.hint = "Message"
        }
    }

    fun onClickSwitch(view: View) {
        if (switch_view.isChecked) {
            message.gravity = Gravity.TOP
        } else {
            message.gravity = Gravity.BOTTOM
        }
    }

    fun onClickCheckbox(view: View) {
        if((view as CheckBox).isChecked) {
            view.setTextColor(Color.RED)
        } else {
            view.setTextColor(Color.BLACK)
        }
        when(view) {
            checkbox1 -> {
                Toast.makeText(this, "Milk!", Toast.LENGTH_SHORT).show()
            }
            checkbox2 -> {
                Toast.makeText(this, "Sugar!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClickRadio(view: View) {
        when(view) {
            radio1 -> {
                radio_group.setBackgroundColor(ContextCompat.getColor(this, R.color.color_blue))
                Toast.makeText(this, "Blue!", Toast.LENGTH_SHORT).show()
            }
            radio2 -> {
                radio_group.setBackgroundColor(ContextCompat.getColor(this, R.color.color_green))
                Toast.makeText(this, "Green!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
