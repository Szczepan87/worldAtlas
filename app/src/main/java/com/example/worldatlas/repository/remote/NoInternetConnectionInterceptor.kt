package com.example.worldatlas.repository.remote

import android.content.Context
import android.net.ConnectivityManager
import com.example.worldatlas.utils.NoInternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NoInternetConnectionInterceptor(context: Context) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response =
        if (isConnected()) chain.proceed(chain.request()) else throw NoInternetConnectionException()

    private fun isConnected(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}