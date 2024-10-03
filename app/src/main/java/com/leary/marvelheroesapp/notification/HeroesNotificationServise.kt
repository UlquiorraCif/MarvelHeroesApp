package com.leary.marvelheroesapp.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class HeroesNotificationService : FirebaseMessagingService() {

    private lateinit var notification: HeroesNotification
    override fun onCreate() {
        super.onCreate()
        notification = HeroesNotification(this)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
        }
        val heroId = message.data["heroId"]
        notification.showNotification(
            message.notification?.title,
            message.notification?.body,
            heroId
        )
    }
}
