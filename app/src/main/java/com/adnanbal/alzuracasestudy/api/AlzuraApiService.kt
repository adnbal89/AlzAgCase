package com.adnanbal.alzuracasestudy.api

import com.adnanbal.alzuracasestudy.api.model.login.LoginResponse
import com.adnanbal.alzuracasestudy.api.model.order.OrderResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface AlzuraApiService {

    companion object {
        const val BASE_URL = "https://api-b2b.alzura.com/"
        const val acceptHeader = "application/vnd.saitowag.api+json;version=1.1"
        const val userName = "106901"
        const val password = "Mobile2022!Dev"
    }

    @Headers("Accept: $acceptHeader")
    @POST("operator/login")
    suspend fun getLoginToken(
        //username: String, password: String
    ): LoginResponse

    @Headers("X-AUTH-TOKEN: NzgzNTQ4NGQzZDQ5MzVmMWYxY2M3Y2UxMmU0ZmUyNjQxODMwZDM5MWIwMGVjMWY4NDkxOTA0ZTI2ZjJkYjc2ZDI3ZjgxMjU1YjZlYTEwMzRmMjUzM2IyY2M3YmI0NjI3ZTliZGYyODZlNDk2ZDA=")
    @GET("operator/orders")
    suspend fun getOrderList(): OrderResponse
}