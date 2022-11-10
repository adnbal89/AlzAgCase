package com.adnanbal.alzuracasestudy.data.login.model

import com.squareup.moshi.Json

data class TokenData(
    @Json(name = "token") val token: String,
    @Json(name = "expire_at") val expireAt: Long
)