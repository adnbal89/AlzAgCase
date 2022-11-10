package com.adnanbal.alzuracasestudy.data.login

import com.adnanbal.alzuracasestudy.api.AlzuraApiService
import com.adnanbal.alzuracasestudy.util.LocalStorage
import java.util.*
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val alzuraApiService: AlzuraApiService,
    private val localStorage: LocalStorage
) {

    suspend fun doLogin(userName: String, password: String): String {
        val credentials = Base64.getEncoder().encode("$userName:$password".toByteArray())
        val token = alzuraApiService.getLoginToken("Basic ${String(credentials)}")

        return token.data.token.apply {
            localStorage.token = this
        }
    }

    fun isLoggedIn(): Boolean = localStorage.token.isNullOrEmpty().not()

    fun doLogout() {
        localStorage.token = null
    }

}