package com.adnanbal.alzuracasestudy.data.order.model

import com.squareup.moshi.Json

data class OrderResponse(
    @Json(name = "data") val data: List<OrderData>
)
