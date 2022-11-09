package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Tax(

    @Json(name = "rate") val rate: Int? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "value") val value: Double? = null

)