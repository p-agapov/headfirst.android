package com.agapovp.android.headfirst1101

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*

class StopwatchFragment : Fragment(), View.OnClickListener {

    private var secs = 0
    private var isRunning = false
    private var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.run {
            secs = getInt(SECS)
            isRunning = getBoolean(IS_RUNNING)
            wasRunning = getBoolean(WAS_RUNNING)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stopwatch, container, false)
            .apply {
                runTimer(this)
                bind(this)
            }
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

    private fun onClickStart() {
        isRunning = true
    }

    private fun onClickStop() {
        isRunning = false
    }

    private fun onClickReset() {
        isRunning = false
        secs = 0
    }

    private fun onClickShare() {
        startActivity(
            Intent(Intent.ACTION_SEND).run {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                putExtra(Intent.EXTRA_TEXT, secs.toString())
                return@run Intent.createChooser(this, getString(R.string.chooser_text))
            }
        )
    }

    private fun runTimer(view: View) {

        Handler().also {
            it.post(object : Runnable {
                override fun run() {
                    val hours = secs / 3_600
                    val minutes = (secs % 3_600) / 60
                    val seconds = secs % 60

                    view.findViewById<TextView>(R.id.time_view).text =
                        String.format(
                            Locale.getDefault(),
                            "%02d:%02d:%02d",
                            hours,
                            minutes,
                            seconds
                        )

                    if (isRunning) {
                        this@StopwatchFragment.secs++
                    }

                    it.postDelayed(this, 1000)
                }
            })
        }
    }

    private fun bind(view: View) {
        view.findViewById<Button>(R.id.start_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.stop_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.reset_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.share_button).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
            R.id.share_button -> onClickShare()
        }
    }

    companion object {
        const val SECS = "secs"
        const val IS_RUNNING = "isRunning"
        const val WAS_RUNNING = "wasRunning"
    }
}
