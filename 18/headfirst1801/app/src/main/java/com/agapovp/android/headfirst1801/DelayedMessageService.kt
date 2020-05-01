package com.agapovp.android.headfirst1801

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.TimeUnit

class DelayedMessageService : IntentService("DelayedMessageService") {

    override fun onHandleIntent(intent: Intent?) {
        TimeUnit.SECONDS.sleep(10)
        intent?.getStringExtra(EXTRA_MESSAGE)?.let { showText(it) }
    }

    private fun showText(text: String) {

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setContentTitle(getString(R.string.button_text))
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(longArrayOf(0, 1000))
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID,
                        getString(R.string.channel_name),
                        NotificationManager.IMPORTANCE_HIGH
                    ).apply { description = getString(R.string.channel_description) }
                )
        }

        val actionPendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        builder.setContentIntent(actionPendingIntent)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
        const val CHANNEL_ID = "channel_id_urgent"
        const val NOTIFICATION_ID = 5453
    }
}
