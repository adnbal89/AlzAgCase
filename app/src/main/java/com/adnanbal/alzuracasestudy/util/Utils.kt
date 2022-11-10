package com.adnanbal.alzuracasestudy.util

import com.adnanbal.alzuracasestudy.api.model.order.Payment

val <T> T.exhaustive: T
    get() = this


fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}

fun getPaymentTypeString(payment: Payment): String {
    return when (payment.id) {
        1 -> "SEPA Direct Debit"
        2 -> "Prepayment"
        3 -> "Cash on delivery"
        4 -> "PayPal/Credit Card"
        5 -> "open payment method"
        6 -> ""
        7 -> "Invoice(8 days payment term)"
        8 -> "open payment method (SEPA)"
        else -> ""
    }

}