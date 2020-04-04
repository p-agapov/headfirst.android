package com.agapovp.android.headfirst0401

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stopwatch.*
import java.util.*

class StopwatchActivity : AppCompatActivity() {

    private var secs = 0
    private var isRunning = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        savedInstanceState?.let {
            secs = it.getInt(SECS)
            isRunning = it.getBoolean(IS_RUNNING)
            wasRunning = it.getBoolean(WAS_RUNNING)
        }

        runTimer()
    }

    override fun onResume() {
        super.onResume()

        if (wasRunning) {
            isRunning = true
        }
    }

    override fun onPause() {
        super.onPause()

        wasRunning = isRunning
        isRunning = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SECS, secs)
        outState.putBoolean(IS_RUNNING, isRunning)
        outState.putBoolean(WAS_RUNNING, wasRunning)
    }

    fun onClickStart(view: View) {
        isRunning = true
    }

    fun onClickStop(view: View) {
        isRunning = false
    }

    fun onClickReset(view: View) {
        isRunning = false
        secs = 0
    }

    fun onClickShare(view: View) {
        startActivity(
            Intent(Intent.ACTION_SEND).run {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                putExtra(Intent.EXTRA_TEXT, secs.toString())
                return@run Intent.createChooser(this, getString(R.string.chooser_text))
            }
        )
    }

    private fun runTimer() {

        Handler().also {
            it.post(object : Runnable {
                override fun run() {
                    val hours = secs / 3_600
                    val minutes = (secs % 3_600) / 60
                    val seconds = secs % 60

                    time_view.text =
                        String.format(
                            Locale.getDefault(),
                            "%02d:%02d:%02d",
                            hours,
                            minutes,
                            seconds
                        )

                    if (isRunning) {
                        this@StopwatchActivity.secs++
                    }

                    it.postDelayed(this, 1000)
                }
            })
        }
    }

    companion object {
        const val SECS = "secs"
        const val IS_RUNNING = "isRunning"
        const val WAS_RUNNING = "wasRunning"
    }
}
