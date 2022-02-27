package github.sachin2dehury.owlmail.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CloudMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationExt: NotificationExt

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification.let { notification ->
            notification?.let {
                notificationExt.notify("${it.title}", "${it.body}")
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}
