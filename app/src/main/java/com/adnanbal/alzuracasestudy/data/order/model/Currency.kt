package com.adnanbal.alzuracasestudy.data.order.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "code") val code: String? = null,
    @Json(name = "factor") val factor: Int? = null
) : Parcelable