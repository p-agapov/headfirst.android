package com.agapovp.android.headfirst0301

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_message.*

class CreateMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)
    }

    fun onSendMessage(view: View) {
        startActivity(
            Intent(this, ReceiveMessageActivity::class.java)
                .putExtra(Intent.EXTRA_TEXT, message.text.toString())
        )
    }

    fun onShareMessage(view: View) {
        startActivity(
            Intent(Intent.ACTION_SEND).run {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
                putExtra(Intent.EXTRA_TEXT, message.text.toString())
                return@run Intent.createChooser(this, getString(R.string.chooser_text))
            }
        )
    }
}
