package com.adnanbal.alzuracasestudy.feature.main.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnanbal.alzuracasestudy.R
import com.adnanbal.alzuracasestudy.databinding.FragmentOrdersBinding
import com.adnanbal.alzuracasestudy.shared.OrderListAdapter
import com.adnanbal.alzuracasestudy.util.extension.setDivider
import com.adnanbal.alzuracasestudy.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private var sortByDesc = true

    private val viewModel: OrdersViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    private val orderListAdapter by lazy {
        OrderListAdapter { orderData ->
            findNavController().navigate(
                OrdersFragmentDirections.actionOrderDetail(orderData!!)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbar) {
            setTitle(R.string.all_available_orders)
            inflateMenu(R.menu.menu_orders)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_sort -> {
                        //trigger date load according to sortType
                        sortByDesc = !sortByDesc
                        getOrders()
                        true
                    }
                    R.id.action_logout -> {
                        viewModel.doLogout()
                        navigator.toLoginActivity()
                            .clearBackStack()
                            .navigate()
                        true
                    }
                    else -> false
                }
            }
        }

        with(binding.recyclerViewOrderList) {
            adapter = orderListAdapter
            layoutManager = LinearLayoutManager(context)
            //divider line, with a bit of a gradient.
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            setHasFixedSize(true)
        }

        viewModel.orderList.observe(viewLifecycleOwner) { orderList ->
            //hide progressBar
            binding.progressBar.isVisible = false
            orderListAdapter.submitList(orderList)
        }

        getOrders()
    }


    private fun getOrders() {
        binding.progressBar.isVisible = true
        orderListAdapter.submitList(emptyList())
        viewModel.getOrdersSortedWith(sortByDesc)
    }
}