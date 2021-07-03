package com.example.cryptocurrencyratekotlin

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.component2
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.mainFragment)
    }

    override fun onBackPressed(){
        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите выйти из программы?")

            setPositiveButton("Да") { _, _ ->
                System.exit(-1)
            }

            setNegativeButton("Нет"){_, _ ->
                Toast.makeText(this@MainActivity, "Thank you",
                    Toast.LENGTH_LONG).show()
            }
            setCancelable(true)
        }.create().show()
    }
}
