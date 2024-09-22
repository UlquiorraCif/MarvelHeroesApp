package com.leary.marvelheroesapp.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class HeroesNotificationService: FirebaseMessagingService() {

    private lateinit var notification: HeroesNotification
    override fun onCreate() {
        super.onCreate()
        notification = HeroesNotification(this)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.notification == null || message.data.isEmpty()) {
            Log.d("FCM Message", "Can't reach a data payload")
        }

        message.notification?.let {
            Log.d("FCM message", "Message body: ${it.body}")

        }

        notification.showNotification(
            message.notification?.title,
            message.notification?.body,
            message.data["heroId"]
        )

    }

    override fun onNewToken(token: String) {
        Log.d("FCM token", "Refreshed token: $token")
    }
}
