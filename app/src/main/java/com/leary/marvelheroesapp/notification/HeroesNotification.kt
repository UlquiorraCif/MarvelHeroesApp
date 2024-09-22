package com.leary.marvelheroesapp.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.leary.marvelheroesapp.MainActivity
import com.leary.marvelheroesapp.R

class HeroesNotification(private val context: Context) {
    companion object {
        const val HEROES_CHANNEl_ID = "heroes_channel"
    }
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(title:String?, text: String?, heroId: String?){
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, HEROES_CHANNEl_ID)
            .setSmallIcon(R.drawable.baseline_boy_24)
            .setContentTitle(title)
            .setContentText(text + " " + heroId)
            .setContentIntent(activityPendingIntent)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()

        notificationManager.notify(1, notification)
    }
}
