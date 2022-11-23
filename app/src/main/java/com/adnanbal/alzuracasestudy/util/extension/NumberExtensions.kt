package com.adnanbal.alzuracasestudy.util

import java.text.DecimalFormat

fun Double.roundTo(n: Int): Double {
    return DecimalFormat("0.00").format(this).toDouble()
}
