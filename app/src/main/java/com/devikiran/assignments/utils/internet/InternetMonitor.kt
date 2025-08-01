package com.devikiran.assignments.utils.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InternetMonitor(context: Context) {

    private val _isConnected = MutableStateFlow<InternetState>(InternetState.IDLE)
    val isConnected: StateFlow<InternetState> = _isConnected

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.update { InternetState.CONNECTED }
        }

        override fun onLost(network: Network) {
            _isConnected.update { InternetState.DISCONNECTED }
        }
    }

    fun register() {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
