package com.adnanbal.alzuracasestudy.feature.splash

import androidx.lifecycle.ViewModel
import com.adnanbal.alzuracasestudy.data.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    fun isLoggedIn() = loginRepository.isLoggedIn()

}