package com.example.cryptocurrencyratekotlin.Notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//Класс реализующий получение push-уведомлений
class PushService : FirebaseMessagingService() {

    //Метод позволяющий получить токен пользователя, по которому отправляется запрос
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
    }

    //Метод обробатывающий получение push-уведомления
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val intent = Intent(INTENT_FILTER)
        remoteMessage.data.forEach { entity ->
            intent.putExtra(entity.key, entity.value)
        }

        sendBroadcast(intent)
    }

    //Константы для определения типа уведомления
    companion object{
        const val INTENT_FILTER = "PUSH_EVENT"
        const val KEY_ACTION = "action"
        const val KEY_MESSAGE = "message"

        const val ACTION_SHOW_MESSAGE = "show_message"
    }
}