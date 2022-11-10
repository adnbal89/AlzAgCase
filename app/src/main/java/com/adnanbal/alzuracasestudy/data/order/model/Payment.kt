package com.adnanbal.alzuracasestudy.data.order.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payment(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null


):Parcelable
