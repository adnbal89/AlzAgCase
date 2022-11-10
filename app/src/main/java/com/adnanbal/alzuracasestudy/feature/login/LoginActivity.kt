package com.adnanbal.alzuracasestudy.feature.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import com.adnanbal.alzuracasestudy.R
import com.adnanbal.alzuracasestudy.databinding.ActivityLoginBinding
import com.adnanbal.alzuracasestudy.util.navigation.Navigator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonLogin.setOnClickListener {
                progressBar.isVisible = true

                val userName = binding.textViewUserName.editText?.text.toString()
                val password = binding.textViewPassword.editText?.text.toString()

                viewModel.tryLogin(userName, password)
            }
        }

        viewModel.loginEvent.observe(this) { status ->
            binding.progressBar.isVisible = false

            when (status) {
                is LoginViewModel.LoginStatus.Success -> {
                    navigator.toMainActivity()
                        .clearBackStack()
                        .navigate()
                }

                is LoginViewModel.LoginStatus.Error -> Snackbar.make(
                    binding.root,
                    getString(R.string.loginError),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}