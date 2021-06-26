package com.example.cryptocurrencyratekotlin.repository

import com.example.cryptocurrencyratekotlin.CruptsRequest
import com.example.cryptocurrencyratekotlin.model.CruptList

internal class CruptListRepository(val cruptsRequest: CruptsRequest) :
    CruptsRepository {

    override val crupts: CruptList?
        get() = cruptsRequest.cruptList
}