package com.adnanbal.alzuracasestudy.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.adnanbal.alzuracasestudy.R
import com.adnanbal.alzuracasestudy.databinding.ActivityLoginBinding
import com.adnanbal.alzuracasestudy.feature.orders.OrdersActivity
import com.adnanbal.alzuracasestudy.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val userName = binding.textViewUserName.editText?.text.toString()
        val password = binding.textViewPassword.editText?.text.toString()

        binding.apply {
            buttonLogin.setOnClickListener {
                progressBar.isVisible = true
                viewModel.tryLogin(userName, password)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginEventChannel.collect { status ->
                    binding.progressBar.isVisible = false
                    when (status) {
                        is LoginViewModel.LoginStatus.Success -> {
                            val intent = Intent(this@LoginActivity, OrdersActivity::class.java)
                            startActivity(intent)
                        }

                        is LoginViewModel.LoginStatus.Error -> Snackbar.make(
                            binding.root,
                            getString(R.string.loginError),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }.exhaustive
                }
            }
        }
    }

}