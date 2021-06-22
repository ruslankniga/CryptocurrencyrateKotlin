package com.example.cryptocurrencyratekotlin.model

import com.example.cryptocurrencyratekotlin.СhangeFormat

class Crupt {
    val id: String? = null
    val rank: String? = null
    val symbol: String? = null
    val name: String? = null
    val supply: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val maxSupply: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val marketCapUsd: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val volumeUsd24Hr: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val priceUsd: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val changePercent24Hr: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val vwap24Hr: String? = null
        get() = СhangeFormat.bdFormat(field).toString()
    val explorer: String? = null

}