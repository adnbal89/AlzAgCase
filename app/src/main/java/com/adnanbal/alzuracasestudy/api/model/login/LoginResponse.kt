package com.adnanbal.alzuracasestudy.api.model.login

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "data") val data: TokenData? = TokenData()
)
