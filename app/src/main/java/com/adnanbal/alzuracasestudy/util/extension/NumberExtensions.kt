package com.adnanbal.alzuracasestudy.util

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}
