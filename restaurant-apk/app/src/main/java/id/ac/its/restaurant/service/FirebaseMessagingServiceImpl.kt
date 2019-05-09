package id.ac.its.restaurant.service

import android.app.Notification
import android.app.NotificationManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.ac.its.restaurant.R

class FirebaseMessagingServiceImpl : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val remoteNotification = remoteMessage.notification
        val notification = Notification.Builder(this)
            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
            .setContentTitle(remoteNotification?.title)
            .setContentText(remoteNotification?.body)
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(0, notification.build())
    }
}
