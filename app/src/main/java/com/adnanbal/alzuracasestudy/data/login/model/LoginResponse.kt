package com.adnanbal.alzuracasestudy.data.login.model

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "data") val data: TokenData
)
