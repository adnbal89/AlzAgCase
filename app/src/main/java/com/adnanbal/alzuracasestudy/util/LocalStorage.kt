package com.adnanbal.alzuracasestudy.util

import android.content.Context

class LocalStorage(
    val context: Context
) {
    val sharedPreferences = context.getSharedPreferences("alzuraPref", Context.MODE_PRIVATE)

    var token: String?
        get() = sharedPreferences.getString("token", null)
        set(value) = sharedPreferences.edit().putString("token", value).apply()
}