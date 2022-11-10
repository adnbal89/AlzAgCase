package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Operator(

    @Json(name = "id") val id: Int? = null,
    @Json(name = "prices") val prices: List<Prices> = arrayListOf(),
    @Json(name = "benefit") val benefit: Double? = null

)