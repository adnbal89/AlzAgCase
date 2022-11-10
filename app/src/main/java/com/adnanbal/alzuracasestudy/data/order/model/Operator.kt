package com.adnanbal.alzuracasestudy.data.order.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Operator(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "prices") val prices: List<Prices> = arrayListOf(),
    @Json(name = "benefit") val benefit: Double? = null
): Parcelable
