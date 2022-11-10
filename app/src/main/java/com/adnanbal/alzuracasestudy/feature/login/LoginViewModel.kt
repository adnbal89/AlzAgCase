package com.adnanbal.alzuracasestudy.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnanbal.alzuracasestudy.data.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    //loginEvents to be sent to the activity for login status.
    private val _loginEvent = MutableLiveData<LoginStatus>()
    val loginEvent: LiveData<LoginStatus> = _loginEvent

    fun tryLogin(userName: String, password: String) {
        viewModelScope.launch {

            try {
                val token = loginRepository.doLogin(userName, password)
                _loginEvent.postValue(LoginStatus.Success(token))
            } catch (e: Exception) {
                println(e)
                _loginEvent.postValue(LoginStatus.Error)
            }
        }
    }

    sealed class LoginStatus {
        data class Success(val token: String) : LoginStatus()
        object Error : LoginStatus()
    }
}