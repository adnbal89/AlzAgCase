package com.adnanbal.alzuracasestudy.feature.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.adnanbal.alzuracasestudy.databinding.ActivitySplashBinding
import com.adnanbal.alzuracasestudy.util.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            if (viewModel.isLoggedIn()) {
                navigator.toMainActivity()
                    .clearBackStack()
                    .navigate()
            } else {
                navigator.toLoginActivity()
                    .clearBackStack()
                    .navigate()
            }
        }, 2000)
    }
}