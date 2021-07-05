package com.example.cryptocurrencyratekotlin

import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.model.CruptRateList
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("assets")
    suspend fun getCrupt(): CruptList?


    @GET("history?interval=d1")
    suspend fun getCruptRate(): CruptRateList?
}