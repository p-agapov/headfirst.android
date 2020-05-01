package com.agapovp.android.headfirst1801

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickButton(view: View) {
        startService(
            Intent(this, DelayedMessageService::class.java).apply {
                putExtra(DelayedMessageService.EXTRA_MESSAGE, resources.getString(R.string.message))
            }
        )
    }
}
