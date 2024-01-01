package com.example.catfacts.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException


/**
 * Interceptor to check and ensure network connectivity before making HTTP requests.
 *
 * @param context The application context used for checking network connectivity.
 */
class NetworkConnectionInterceptor(val context: Context) : Interceptor {
    /**
     * Intercepts the HTTP request to ensure network connectivity before proceeding.
     *
     * @param chain The interceptor chain.
     * @return The response from the server if the device is connected; otherwise, an [IOException] is thrown.
     * @throws IOException Thrown if there is no network connectivity.
     */
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        if (!isConnected(context=context)) {
            Log.i("retrofit", "there is no connection")
            throw IOException()
        } else {
            val builder = chain.request().newBuilder()
            return@run chain.proceed(builder.build())
        }
    }

    /**
     * Checks whether the device is currently connected to a network.
     *
     * @param context The application context used for checking network connectivity.
     * @return `true` if the device is connected; `false` otherwise.
     */
    fun isConnected(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }
}
