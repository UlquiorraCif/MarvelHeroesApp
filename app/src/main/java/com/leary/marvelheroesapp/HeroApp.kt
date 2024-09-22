package com.leary.marvelheroesapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.leary.marvelheroesapp.notification.HeroesNotification
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HeroApp: Application() {
    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                HeroesNotification.HEROES_CHANNEl_ID,
                "counter",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.description = "Used for informing you about hero"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)

        }
    }
}