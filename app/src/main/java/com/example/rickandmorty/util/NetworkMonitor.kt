package com.example.rickandmorty.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.rickandmorty.ui.view.MainActivity.Variables.isNetworkConnected

class NetworkMonitor(context: Context) : NetworkCallback() {

    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isNetworkConnected.postValue(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        isNetworkConnected.postValue(false)
    }

    fun registerNetworkCallbackEvents() {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }
}
