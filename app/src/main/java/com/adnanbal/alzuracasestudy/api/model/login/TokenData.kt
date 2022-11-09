package com.adnanbal.alzuracasestudy.api.model.login

import com.squareup.moshi.Json

data class TokenData(
    @Json(name = "token") var token: String? = null,
    @Json(name = "expire_at") var expireAt: Long? = null,
)