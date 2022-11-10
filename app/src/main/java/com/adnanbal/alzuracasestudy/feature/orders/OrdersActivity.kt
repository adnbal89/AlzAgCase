package com.adnanbal.alzuracasestudy.feature.orders

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adnanbal.alzuracasestudy.R
import com.adnanbal.alzuracasestudy.databinding.ActivityOrdersBinding
import com.adnanbal.alzuracasestudy.shared.OrderListAdapter
import com.adnanbal.alzuracasestudy.util.extension.setDivider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding
    private var sortState = DATE_SORT_BY_DESC

    private val viewModel: OrdersViewModel by viewModels()

    private val orderListAdapter by lazy {
        //implement onItemClick
        //TODO: implement onItemClick and create OrderDetails page if there is time left
        OrderListAdapter { orderData ->
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setTitle(R.string.all_available_orders)

        //initial date load sorted by desc order
        viewModel.getOrdersSortedWith(DATE_SORT_BY_DESC)
        sortState = DATE_SORT_BY_DESC


        with(binding.recyclerViewOrderList) {
            adapter = orderListAdapter
            layoutManager = LinearLayoutManager(context)
            //divider line, with a bit of a gradient.
            setDivider(drawableRes = R.drawable.bg_divider, showLastDivider = false)
            setHasFixedSize(true)

            //to be able to scroll to top on recyclerview on sort click.
            adapter?.apply {
                registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onChanged() {
                        this@with.scrollToPosition(0)
                    }

                    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                        this@with.scrollToPosition(0)
                    }

                    override fun onItemRangeMoved(
                        fromPosition: Int,
                        toPosition: Int,
                        itemCount: Int
                    ) {
                        this@with.scrollToPosition(0)
                    }

                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        this@with.scrollToPosition(0)
                    }

                    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                        this@with.scrollToPosition(0)
                    }

                    override fun onItemRangeChanged(
                        positionStart: Int,
                        itemCount: Int,
                        payload: Any?
                    ) {
                        this@with.scrollToPosition(0)
                    }
                })
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderList.collect { orderList ->

                    //hide progressBar
                    binding.progressBar.isVisible = false
                    orderListAdapter.submitList(orderList)
                }
            }
        }

        //show progress bar while loading
        binding.progressBar.isVisible = true

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_orders, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_sort -> {
                println("SOrt Clicked")
                //trigger date load according to sortType
                if (sortState == DATE_SORT_BY_DESC) {
                    viewModel.getOrdersSortedWith(DATE_SORT_BY_ASC)
                    sortState = DATE_SORT_BY_ASC
                } else {
                    viewModel.getOrdersSortedWith(DATE_SORT_BY_DESC)
                    sortState = DATE_SORT_BY_DESC
                }
                true
            }
            R.id.action_logout -> {
                //TODO: Remove the auth token
                println("logout")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    companion object SortType {
        const val DATE_SORT_BY_ASC = "+updated_at"
        const val DATE_SORT_BY_DESC = "-updated_at"
    }
}