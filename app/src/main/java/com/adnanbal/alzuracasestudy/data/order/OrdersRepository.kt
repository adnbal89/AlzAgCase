package com.adnanbal.alzuracasestudy.data.order

import com.adnanbal.alzuracasestudy.api.AlzuraApiService
import com.adnanbal.alzuracasestudy.data.order.model.OrderData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val alzuraApiService: AlzuraApiService
) {


    fun getAllOrders(sortByDesc: Boolean): Flow<List<OrderData>> =
        flow {
            try {
                val response =
                    alzuraApiService.getOrderList(sort = if (sortByDesc) DATE_SORT_BY_DESC else DATE_SORT_BY_ASC)
                emit(response.data)
            } catch (t: Throwable) {
                //emit throwable in a Resource sealed class object and handle in the activity
                //show httpexception error
                //emit(Resource.Error(t))
            }
        }

    companion object SortType {
        private const val DATE_SORT_BY_ASC = "+updated_at"
        private const val DATE_SORT_BY_DESC = "-updated_at"
    }
}