package com.example.cryptocurrencyratekotlin.model

import com.example.cryptocurrencyratekotlin.СhangeFormat

//Класс хранящий стоимость криптовалюты в определённый период времени
class CruptRate {

    val priceUsd: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
}