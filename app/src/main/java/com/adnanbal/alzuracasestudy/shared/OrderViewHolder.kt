package com.adnanbal.alzuracasestudy.shared

import androidx.recyclerview.widget.RecyclerView
import com.adnanbal.alzuracasestudy.data.order.model.OrderData
import com.adnanbal.alzuracasestudy.databinding.ItemOrderBinding
import com.adnanbal.alzuracasestudy.util.roundTo

class OrderViewHolder(
    private val binding: ItemOrderBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        //set a click listener on each item in the list.
        with(binding) {
            root.setOnClickListener {
                val itemPosition = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    onItemClick(itemPosition)
                }
            }
        }
    }

    fun bind(order: OrderData) {
        with(binding) {
            textViewOrderIdValue.text = order.id.toString()
            textViewDateValue.text = order.updatedAt

            textViewOperatorPriceValue.text =
                order.totalOperatorGrossPriceAmount.roundTo(2).toString()
                    .plus(" ").plus(order.currency?.code.toString())

            textViewPaymentMethodValue.text = order.payment?.name.toString()
        }
    }

}