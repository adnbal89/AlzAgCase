package com.adnanbal.alzuracasestudy.api

import com.adnanbal.alzuracasestudy.api.model.login.LoginResponse
import com.adnanbal.alzuracasestudy.api.model.order.OrderResponse
import retrofit2.http.*


interface AlzuraApiService {

    companion object {
        const val BASE_URL = "https://api-b2b.alzura.com/"
        const val acceptHeader = "application/vnd.saitowag.api+json;version=1.1"
        const val userName = "106901"
        const val password = "Mobile2022!Dev"
    }


    //Burada req atarken, retrofite  username ve pass geçirmem lazım, ama apiproviderı hilt olusturuyor
    @Headers("Accept: $acceptHeader")
    @POST("operator/login")
    suspend fun getLoginToken(
        //username: String, password: String
    ): LoginResponse

    @Headers(
        "Accept: $acceptHeader"
    )
    @GET("operator/orders")
    suspend fun getOrderList(
        @Header("X-AUTH-TOKEN") tokenString: String = "NzgzNTQ4NGQzZDQ5MzVmMWYxY2M3Y2UxMmU0ZmUyNjQxODMwZDM5MWIwMGVjMWY4NDkxOTA0ZTI2ZjJkYjc2ZDI3ZjgxMjU1YjZlYTEwMzRmMjUzM2IyY2M3YmI0NjI3ZTliZGYyODZlNDk2ZDA=",
        //I take into account that, state: 50 is available orders.
        @Query("filter[state]") state: String? = "in;50",
        @Query("sort") sort: String
    ): OrderResponse
}