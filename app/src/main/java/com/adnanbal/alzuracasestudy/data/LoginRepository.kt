package com.adnanbal.alzuracasestudy.data

import com.adnanbal.alzuracasestudy.api.AlzuraApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val alzuraApiService: AlzuraApiService
) {

    suspend fun getLoginToken(userName: String, password: String) =
        alzuraApiService.getLoginToken()
    //alzuraApiService.getLoginToken(userName, password)
}