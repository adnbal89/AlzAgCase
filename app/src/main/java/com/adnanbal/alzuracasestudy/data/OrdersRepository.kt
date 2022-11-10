package com.adnanbal.alzuracasestudy.data

import com.adnanbal.alzuracasestudy.api.AlzuraApiService
import com.adnanbal.alzuracasestudy.api.model.order.OrderData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val alzuraApiService: AlzuraApiService
) {


    fun getAllOrders(sort: String): Flow<List<OrderData>> =
        flow {
            try {
                val response = alzuraApiService.getOrderList(sort = sort)
                //refactor to Resource.Success(response.data)
                emit(response.data)
            } catch (t: Throwable) {
                //emit throwable in a Resource sealed class object and handle in the activity
                //show httpexception error
                //emit(Resource.Error(t))
            }
        }
}