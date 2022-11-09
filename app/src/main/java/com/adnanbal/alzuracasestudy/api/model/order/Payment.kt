package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class Payment(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null
)
