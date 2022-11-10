package com.adnanbal.alzuracasestudy.data.order.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prices(
    @Json(name = "gross") val gross: Double? = null,
    @Json(name = "net") val net: Double? = null,
    @Json(name = "tax") val tax: Tax? = Tax()
) : Parcelable