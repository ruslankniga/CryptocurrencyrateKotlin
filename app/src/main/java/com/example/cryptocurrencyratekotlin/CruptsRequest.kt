package com.example.cryptocurrencyratekotlin

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyratekotlin.model.CruptList
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

//Класс реализующий запрос на список криптовалют
class CruptsRequest(val context: Context, val path: String, val fragment: RecycleViewFragment) {

    var cruptList : CruptList? = null

    //Метод в котором происходит запрос
    fun makeRequest(){

        val retrofit = Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api = retrofit.create(Api::class.java)

        GlobalScope.launch(Dispatchers.Main){

            try {
                cruptList = api.getCrupt()
                fragment.setAdapter()
            } catch (e: NumberFormatException){
                return@launch
            }
        }
    }
}



