package com.example.cryptocurrencyratekotlin.repository

import com.example.cryptocurrencyratekotlin.model.CruptList
import com.example.cryptocurrencyratekotlin.model.CruptRateList

internal interface BaseRepository : CruptsRepository, RatesCruptRepository


internal interface CruptsRepository {
    val crupts: CruptList?
}

internal interface RatesCruptRepository {
    val rates: CruptRateList?
}