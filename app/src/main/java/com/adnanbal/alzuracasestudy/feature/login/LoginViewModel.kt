package com.adnanbal.alzuracasestudy.feature.login

import android.content.Context
import android.content.SharedPreferences
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
    private val loginRepository: LoginRepository,
    private val applicationContext: Context
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

    fun saveAuthTokenToSharedPrefs(authToken: String?) {
        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences("authTokenPref", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit()
        editor.putString("authToken", authToken).apply()
    }

    fun saveUserCredentialsToSharedPrefs(userName: String?, password: String?) {
        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences("authTokenPref", Context.MODE_PRIVATE);
        val editor = sharedPreferences.edit()
        editor.putString("userName", userName)
        editor.putString("password", password).apply()
    }

}