package com.adnanbal.alzuracasestudy.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanbal.alzuracasestudy.api.model.login.LoginResponse
import com.adnanbal.alzuracasestudy.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    //loginEvents to be sent to the activity for login status.
    private val _loginEventChannel = Channel<LoginStatus>()
    val loginEventChannel = _loginEventChannel.receiveAsFlow()

    fun tryLogin(userName: String, password: String) {
        viewModelScope.launch {
            var loginResult = LoginResponse()
            try {
                loginResult = loginRepository.getLoginToken(userName, password)
            } catch (e: Exception) {
                _loginEventChannel.send(LoginStatus.Error)
            }

            println("sonuc " + loginResult.data?.token)
            if (!loginResult.data?.token.isNullOrBlank()) {
                _loginEventChannel.send(LoginStatus.Success(loginResult.data?.token))
            } else {
                _loginEventChannel.send(LoginStatus.Error)
            }
        }
    }

    sealed class LoginStatus {
        data class Success(val tokenData: String?) : LoginStatus()
        object Error : LoginStatus()
    }

}