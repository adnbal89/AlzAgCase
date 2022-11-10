package com.adnanbal.alzuracasestudy.shared

import androidx.recyclerview.widget.DiffUtil
import com.adnanbal.alzuracasestudy.data.order.model.OrderData

class OrderComparator : DiffUtil.ItemCallback<OrderData>() {

    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData) =
        oldItem == newItem

}