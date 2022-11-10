package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class OrderResponse(
    @Json(name = "data") val data: List<OrderData>
)
