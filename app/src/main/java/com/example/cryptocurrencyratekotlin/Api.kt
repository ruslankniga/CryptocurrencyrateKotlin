package com.example.cryptocurrencyratekotlin

import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.model.CruptRateList
import retrofit2.Call
import retrofit2.http.GET

//Интерфейс для составления запросов
interface Api {

    //Запрос на получения криптовалют
    @GET("assets")
    suspend fun getCrupt(): CruptList?

    //Запрос на получения стоимости криптовалюты в определённый период времени
    @GET("history?interval=d1")
    suspend fun getCruptRate(): CruptRateList?
}