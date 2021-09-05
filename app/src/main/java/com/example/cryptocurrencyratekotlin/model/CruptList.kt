package com.example.cryptocurrencyratekotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Класс хранящий список объектов класса криптовалют
class CruptList {

    @SerializedName("data")
    @Expose
    var crupts: List<Crupt>? = null

    fun getByIndex(index: Int): Crupt {
        return crupts!![index]
    }

    val size: Int
        get() = crupts!!.size
}