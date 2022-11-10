package com.adnanbal.alzuracasestudy.util.navigation

import android.app.Activity
import android.content.Intent
import com.adnanbal.alzuracasestudy.feature.login.LoginActivity
import com.adnanbal.alzuracasestudy.feature.main.MainActivity
import com.adnanbal.alzuracasestudy.feature.splash.SplashActivity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class Navigator @Inject constructor(private val activity: Activity) {

    fun toSplashActivity(): NavigationCreator = NavigationCreator(activity)
        .intent(Intent(activity, SplashActivity::class.java))

    fun toLoginActivity(): NavigationCreator = NavigationCreator(activity)
        .intent(Intent(activity, LoginActivity::class.java))

    fun toMainActivity(): NavigationCreator = NavigationCreator(activity)
        .intent(Intent(activity, MainActivity::class.java))

}
