package com.example.cryptocurrencyratekotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.example.cryptocurrencyratekotlin.СhangeFormat

class Crupt(parcel: Parcel) : Parcelable {
    var id: String = ""
    var rank: String = ""
    var symbol: String = ""
    var name: String = ""
    var supply: String = ""
        get() = СhangeFormat.bdFormat(field).toString()
    var maxSupply: String = ""
        get() = СhangeFormat.bdFormat(field).toString()
    var marketCapUsd: String = ""
        get() = СhangeFormat.bdFormat(field).toString()
    var volumeUsd24Hr: String = ""
        get() = СhangeFormat.bdFormat(field).toString()
    var priceUsd: String = ""
        get() = СhangeFormat.bdFormat(field).toString()
    var changePercent24Hr: String = ""
        get() = СhangeFormat.bdFormat(field).toString()
    var vwap24Hr: String = ""
        get() = СhangeFormat.bdFormat(field).toString()

    init {
        val data = arrayOfNulls<String>(11)
        parcel.readStringArray(data)
        id = data[0].toString()
        rank = data[1].toString()
        symbol = data[2].toString()
        name = data[3].toString()
        supply = data[4].toString()
        maxSupply = data[5].toString()
        marketCapUsd = data[6].toString()
        volumeUsd24Hr = data[7].toString()
        priceUsd = data[8].toString()
        changePercent24Hr = data[9].toString()
        vwap24Hr = data[10].toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringArray(arrayOf(id, rank, symbol, name, supply, maxSupply, marketCapUsd, volumeUsd24Hr,
                priceUsd, changePercent24Hr, vwap24Hr))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Crupt> {
        override fun createFromParcel(parcel: Parcel): Crupt {
            return Crupt(parcel)
        }

        override fun newArray(size: Int): Array<Crupt?> {
            return arrayOfNulls(size)
        }
    }
}