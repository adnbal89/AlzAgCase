package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Currency(

    @Json(name = "id") val id: Int? = null,
    @Json(name = "code") val code: String? = null,
    @Json(name = "factor") val factor: Int? = null

)