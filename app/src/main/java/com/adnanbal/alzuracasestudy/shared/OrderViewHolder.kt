package com.adnanbal.alzuracasestudy.shared

import androidx.recyclerview.widget.RecyclerView
import com.adnanbal.alzuracasestudy.api.model.order.OrderData
import com.adnanbal.alzuracasestudy.databinding.ItemOrderBinding
import com.adnanbal.alzuracasestudy.util.roundTo

class OrderViewHolder(
    private val binding: ItemOrderBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        //set a click listener on each item in the list.
        binding.apply {
            root.setOnClickListener {
                val itemPosition = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    onItemClick(itemPosition)
                }
            }
        }
    }

    fun bind(order: OrderData) {
        binding.apply {
            textViewOrderIdValue.text = order.id.toString()
            textViewDateValue.text = order.updatedAt
            var totalOperatorGrossPriceAmount = 0.0
            order.operator?.prices?.forEach { prices ->
                totalOperatorGrossPriceAmount += prices.gross.let { it!! }
            }
            textViewOperatorPriceValue.text =
                totalOperatorGrossPriceAmount.roundTo(2).toString()
                    .plus(" ").plus(order.currency?.code.toString())

            textViewPaymentMethodValue.text = order.payment?.name.toString()
        }
    }

}