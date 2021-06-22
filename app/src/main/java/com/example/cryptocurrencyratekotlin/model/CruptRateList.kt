package com.example.cryptocurrencyratekotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CruptRateList {

    @SerializedName("data")
    @Expose
    var cruptRates: List<CruptRate>? = null

    fun getCrupts(): List<CruptRate>? {
        return cruptRates
    }

    fun setCrupts(cruptRates: List<CruptRate>?) {
        this.cruptRates = cruptRates
    }

    fun getByIndex(index: Int): CruptRate? {
        return cruptRates!![index]
    }

    val size: Int
        get() = cruptRates!!.size

}