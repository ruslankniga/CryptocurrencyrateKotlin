package com.example.cryptocurrencyratekotlin

import android.content.Context
import android.widget.Toast
import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.model.CruptRate
import com.example.cryptocurrencyratekotlin.model.CruptRateList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RatesRequest(val context: Context, val path: String) {

    var ratesList: CruptRateList? = null

    fun makeRequest(){

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api: Api = retrofit.create(Api::class.java)
        val call: Call<CruptRateList> = api.getCruptRate()
        call.enqueue(object : Callback<CruptRateList> {
            override fun onResponse(call: Call<CruptRateList>, response: Response<CruptRateList>) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
                }

                ratesList = response.body()
            }

            override fun onFailure(call: Call<CruptRateList>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}