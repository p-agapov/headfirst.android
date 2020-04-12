package com.agapovp.android.headfirst1301

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setSupportActionBar(activity_order_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickDone(view: View) {
        Snackbar.make(
            activity_order_coordinator,
            getString(R.string.activity_order_snackbar_text),
            Snackbar.LENGTH_LONG
        ).run {
            setAction(R.string.activity_order_snackbar_action_text) {
                Toast.makeText(
                    this@OrderActivity,
                    R.string.activity_order_toast_text,
                    Toast.LENGTH_SHORT
                ).show()
            }
            show()
        }
    }
}
