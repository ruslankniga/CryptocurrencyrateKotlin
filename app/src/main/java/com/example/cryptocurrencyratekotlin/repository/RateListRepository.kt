package com.example.cryptocurrencyratekotlin.repository

import com.example.cryptocurrencyratekotlin.CruptsRequest
import com.example.cryptocurrencyratekotlin.RatesRequest
import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.model.CruptRateList

class RateListRepository (val ratessRequest: RatesRequest) {

    fun getRateList() : CruptRateList?
    {
        return ratessRequest.ratesList
    }
}