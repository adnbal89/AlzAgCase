package com.adnanbal.alzuracasestudy.api.model.login

import com.squareup.moshi.Json

data class TokenData(
    @Json(name = "token") val token: String? = null,
    @Json(name = "expire_at") val expireAt: Long? = null,
)