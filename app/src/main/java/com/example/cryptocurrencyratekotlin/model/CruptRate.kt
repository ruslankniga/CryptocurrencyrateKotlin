package com.example.cryptocurrencyratekotlin.model

import com.example.cryptocurrencyratekotlin.СhangeFormat

class CruptRate {

    val priceUsd: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
}