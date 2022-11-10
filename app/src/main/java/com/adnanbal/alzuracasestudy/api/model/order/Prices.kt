package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Prices(

    @Json(name = "gross") val gross: Double? = null,
    @Json(name = "net") val net: Double? = null,
    @Json(name = "tax") val tax: Tax? = Tax()

)