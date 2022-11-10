package com.adnanbal.alzuracasestudy.data.order.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tax(
    @Json(name = "rate") val rate: Int? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "value") val value: Double? = null
) : Parcelable