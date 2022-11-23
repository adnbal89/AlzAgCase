package com.adnanbal.alzuracasestudy.feature.main.orders.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adnanbal.alzuracasestudy.R
import com.adnanbal.alzuracasestudy.databinding.FragmentOrderDetailBinding
import com.adnanbal.alzuracasestudy.feature.main.orders.OrdersViewModel
import com.adnanbal.alzuracasestudy.util.navigation.Navigator
import com.adnanbal.alzuracasestudy.util.roundTo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderDetailsFragment : Fragment() {
    private lateinit var binding: FragmentOrderDetailBinding

    private val args: OrderDetailsFragmentArgs by navArgs()
    private val viewModel: OrdersViewModel by viewModels()


    @Inject
    lateinit var navigator: Navigator

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

        with(binding.toolbar) {
            title = getString(R.string.order_id).plus(" ").plus(args.order.id.toString())
            inflateMenu(R.menu.menu_orders)
            //hide sort button in this fragment
            menu.findItem(R.id.action_sort).isVisible = false

            setOnMenuItemClickListener {
                when (it.itemId) {
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

        with(binding) {
            textViewCreatedAtValue.text = args.order.createdAt
            textViewPaymentUpdatedAtValue.text = args.order.updatedAt

            textViewOperatorPriceValue.text =
                args.order.totalOperatorGrossPriceAmount.roundTo(2).toString()
                    .plus(" ").plus(args.order.currency?.code.toString())

            textViewPaymentMethodValue.text = args.order.payment?.name.toString()
            textViewCustomerIdValue.text = args.order.customer?.id.toString()
            textViewOperatorIdValue.text = args.order.operator?.id.toString()
            textViewSumOriginalPriceValue.text = args.order.sumOriginalPrice.toString()
        }

        //back pressed handle
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

}