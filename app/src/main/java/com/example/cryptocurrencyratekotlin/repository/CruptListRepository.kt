package com.example.cryptocurrencyratekotlin.repository

import com.example.cryptocurrencyratekotlin.CruptsRequest
import com.example.cryptocurrencyratekotlin.RecycleViewFragment
import com.example.cryptocurrencyratekotlin.model.CruptList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal class CruptListRepository(val cruptsRequest: CruptsRequest, recycleFragment: RecycleViewFragment){

    fun getCruptList() : CruptList? {
        return cruptsRequest.cruptList
    }

}