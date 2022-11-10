package com.adnanbal.alzuracasestudy.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.adnanbal.alzuracasestudy.data.order.model.OrderData
import com.adnanbal.alzuracasestudy.databinding.ItemOrderBinding

class OrderListAdapter(
    var onItemClick: (OrderData?) -> Unit
) : ListAdapter<OrderData, OrderViewHolder>(OrderComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return OrderViewHolder(
            binding,
            onItemClick = { itemPosition ->
                val order = getItem(itemPosition)
                if (order != null) {
                    onItemClick(order)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}