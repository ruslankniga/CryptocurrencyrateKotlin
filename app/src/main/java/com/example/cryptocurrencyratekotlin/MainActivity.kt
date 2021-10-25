package com.example.cryptocurrencyratekotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cryptocurrencyratekotlin.Notification.PushService
import com.google.firebase.messaging.FirebaseMessaging

//Класс реализующий главную активити
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var pushBroadcastReceiver: BroadcastReceiver

    //Инициализация переменных
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.mainFragment)

        startService(Intent(this, MyService::class.java))

        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if (!task.isSuccessful){
                return@addOnCompleteListener
            }

            val token = task.result
            Log.e("TAG", "Token -> $token")
        }

        pushBroadcastReceiver = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val extras = intent?.extras
                extras?.keySet()?.firstOrNull { it == PushService.KEY_ACTION }?.let { key ->
                    when (extras.getString(key)) {
                        PushService.ACTION_SHOW_MESSAGE -> {
                            extras.getString(PushService.KEY_MESSAGE)?.let { message ->
                                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        else -> Log.e("TAG", "No needed key found")
                    }
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(PushService.INTENT_FILTER)

        registerReceiver(pushBroadcastReceiver, intentFilter)
    }

    //Очистка ресурсов
    override fun onDestroy() {
        unregisterReceiver(pushBroadcastReceiver)
        super.onDestroy()
    }

    //Метод переопределяющий событие при нажатии на клавишу "Назад"
    override fun onBackPressed(){
        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите выйти из программы?")

            setPositiveButton("Да") { _, _ ->


                System.exit(-1)
            }

            setNegativeButton("Нет"){ _, _ ->
                Toast.makeText(
                    this@MainActivity, "Thank you",
                    Toast.LENGTH_LONG
                ).show()
            }
            setCancelable(true)
        }.create().show()
    }
}
