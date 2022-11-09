package com.adnanbal.alzuracasestudy.feature.orders

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import com.adnanbal.alzuracasestudy.R
import com.adnanbal.alzuracasestudy.databinding.ActivityOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setTitle(R.string.all_orders)

        //show progress bar while loading
        binding.progressBar.isVisible = true

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_orders, menu)
        return true
    }

    override fun onResume() {
        //TODO : refactor this
        binding.progressBar.isVisible = false
        super.onResume()
    }
}