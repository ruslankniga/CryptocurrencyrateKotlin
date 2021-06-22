package com.example.cryptocurrencyratekotlin

import java.math.BigDecimal
import java.math.RoundingMode

interface СhangeFormat {
    companion object {
        fun bdFormat(value: String?): Double {
            require(2 >= 0)
            if (value != null) {
                var bd: BigDecimal
                bd = BigDecimal(value)
                bd = bd.setScale(2, RoundingMode.HALF_UP)
                return bd.toDouble()
            }
            return 0.0
        }
    }
}