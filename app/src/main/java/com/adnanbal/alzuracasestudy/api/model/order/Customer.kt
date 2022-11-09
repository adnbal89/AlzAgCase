package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Customer(

    @Json(name = "id") val id: Int? = null,
    @Json(name = "prices") val prices: ArrayList<Prices> = arrayListOf(),
    @Json(name = "benefit") val benefit: Int? = null

)