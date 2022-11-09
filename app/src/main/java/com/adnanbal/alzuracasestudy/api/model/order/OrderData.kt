package com.adnanbal.alzuracasestudy.api.model.order

import com.squareup.moshi.Json

data class OrderData(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "state") val state: Int? = null,
    @Json(name = "payment") val payment: Payment? = Payment(),
    @Json(name = "currency") val currency: Currency? = Currency(),
    @Json(name = "sum_original_price") val sumOriginalPrice: Double? = null,
    @Json(name = "operator") val operator: Operator? = Operator(),
    @Json(name = "customer") val customer: Customer? = Customer()
)
