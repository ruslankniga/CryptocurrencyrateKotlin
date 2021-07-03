package com.example.cryptocurrencyratekotlin

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrencyratekotlin.model.CruptList
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.Continuation
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume

class CruptsRequest(val context: Context, val path: String) {

    var cruptList: CruptList? = null


    fun makeRequest() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api: Api = retrofit.create(Api::class.java)
        val call: Call<CruptList> = api.getCrupt()
        call.enqueue(object : Callback<CruptList> {
            override fun onResponse(call: Call<CruptList>, response: Response<CruptList>) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
                }

                cruptList = response.body()
            }

            override fun onFailure(call: Call<CruptList>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}


