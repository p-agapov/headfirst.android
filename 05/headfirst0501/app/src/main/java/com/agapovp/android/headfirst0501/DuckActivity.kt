package com.agapovp.android.headfirst0501

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DuckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duck)
    }

    fun onClickImage(view: View) {
        startActivity(Intent(this, LayoutExamplesActivity::class.java))
    }
}
