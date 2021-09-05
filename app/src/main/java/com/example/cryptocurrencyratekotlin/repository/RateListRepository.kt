package com.example.cryptocurrencyratekotlin.repository

import com.example.cryptocurrencyratekotlin.CruptsRequest
import com.example.cryptocurrencyratekotlin.RatesRequest
import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.model.CruptRateList

//Класс реализующий репозиторий запроса для получения списка стоимости криптовалюты в определённый период времени
class RateListRepository (val ratessRequest: RatesRequest) {

    //Функция для получения данных с запроса
    fun getRateList() : CruptRateList?
    {
        return ratessRequest.ratesList
    }
}