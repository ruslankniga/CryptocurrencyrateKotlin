package com.example.cryptocurrencyratekotlin.repository

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyratekotlin.CruptsRequest
import com.example.cryptocurrencyratekotlin.model.CruptList

//Класс реализующий репозиторий запроса для получения списка криптовалют
internal class CruptListRepository(val cruptsRequest: CruptsRequest) {

    //Функция для получения данных с запроса
    fun getCruptList(): CruptList? {
        return cruptsRequest.cruptList
    }
}