package com.example.cryptocurrencyratekotlin.repository

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencyratekotlin.CruptsRequest
import com.example.cryptocurrencyratekotlin.model.CruptList

internal class CruptListRepository(val cruptsRequest: CruptsRequest) {


    fun getCruptList(): CruptList? {
        return cruptsRequest.cruptList
    }
}