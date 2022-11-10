package com.adnanbal.alzuracasestudy.api

import com.adnanbal.alzuracasestudy.data.login.model.LoginResponse
import com.adnanbal.alzuracasestudy.data.order.model.OrderResponse
import retrofit2.http.*


interface AlzuraApiService {

    companion object {
        const val BASE_URL = "https://api-b2b.alzura.com/"
        const val acceptHeader = "application/vnd.saitowag.api+json;version=1.1"
    }

    @Headers("Accept: $acceptHeader")
    @POST("operator/login")
    suspend fun getLoginToken(@Header("Authorization") credentials: String): LoginResponse

    @Headers("Accept: $acceptHeader")
    @GET("operator/orders")
    suspend fun getOrderList(
        //I take into account that, state: 50 is available orders.
        @Query("filter[state]") state: String? = "in;50",
        @Query("sort") sort: String
    ): OrderResponse
}