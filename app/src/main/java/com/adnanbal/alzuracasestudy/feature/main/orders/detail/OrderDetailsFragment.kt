package com.adnanbal.alzuracasestudy.feature.main.orders.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adnanbal.alzuracasestudy.databinding.FragmentOrderDetailBinding
import com.adnanbal.alzuracasestudy.util.roundTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailsFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailBinding
    private val args: OrderDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            textViewOrderIdValue.text = args.order.id.toString()
            textViewDateValue.text = args.order.updatedAt

            textViewOperatorPriceValue.text =
                args.order.totalOperatorGrossPriceAmount.roundTo(2).toString()
                    .plus(" ").plus(args.order.currency?.code.toString())

            textViewPaymentMethodValue.text = args.order.payment?.name.toString()
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

}