package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Prices(

    @Json(name = "gross") var gross: Double? = null,
    @Json(name = "net") var net: Double? = null,
    @Json(name = "tax") var tax: Tax? = Tax()

)