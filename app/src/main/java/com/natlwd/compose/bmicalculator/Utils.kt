package com.natlwd.compose.bmicalculator

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(): Double? {
    return try {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        df.format(this).toDouble()
    }catch (e:Exception) {
        e.printStackTrace()
        null
    }
}