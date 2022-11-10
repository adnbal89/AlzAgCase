package com.adnanbal.alzuracasestudy.feature.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanbal.alzuracasestudy.api.model.order.OrderData
import com.adnanbal.alzuracasestudy.data.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersRepository: OrdersRepository
) : ViewModel() {

    private val _orderList = MutableStateFlow<List<OrderData>>(
        mutableListOf()
    )
    val orderList: StateFlow<List<OrderData>>
        get() = _orderList

    fun getOrdersSortedWith(sortBy: String) {
        viewModelScope.launch {
            ordersRepository.getAllOrders(sortBy).collect { list ->
                _orderList.value = list
            }
        }
    }
}
