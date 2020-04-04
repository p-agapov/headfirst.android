package com.agapovp.android.headfirst0301

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_receive_message.*

class ReceiveMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)

        message.text = intent.getStringExtra(Intent.EXTRA_TEXT)
    }
}
