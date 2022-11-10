package com.adnanbal.alzuracasestudy.feature.main.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanbal.alzuracasestudy.data.login.LoginRepository
import com.adnanbal.alzuracasestudy.data.order.OrdersRepository
import com.adnanbal.alzuracasestudy.data.order.model.OrderData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersRepository: OrdersRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _orderList = MutableLiveData<List<OrderData>>()
    val orderList: LiveData<List<OrderData>>
        get() = _orderList

    fun getOrdersSortedWith(sortByDesc: Boolean) {
        viewModelScope.launch {
            ordersRepository.getAllOrders(sortByDesc).collect { list ->
                _orderList.value = list
            }
        }
    }

    fun doLogout() {
        loginRepository.doLogout()
    }
}
