package com.alexkuz.data.network

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.alexkuz.data.coroutine.ApplicationCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface NetworkStateProvider {
    val state: StateFlow<Boolean>
    val isNetworkAvailable: Boolean
}

private const val STOP_TIMEOUT_MILLIS = 1_000L
private const val REPLAY_EXPIRATION_MILLiS = 1_000L

@SuppressLint("MissingPermission")
class NetworkStateProviderImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    @ApplicationCoroutineScope private val scope: CoroutineScope,
) : NetworkStateProvider {

    override val isNetworkAvailable get() = connectivityManager.isDefaultNetworkActive
    override val state = connectivityManager
        .networkChangesFlow()
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = STOP_TIMEOUT_MILLIS,
                replayExpirationMillis = REPLAY_EXPIRATION_MILLiS
            ),
            initialValue = isNetworkAvailable,
        )

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    private fun ConnectivityManager.networkChangesFlow() = callbackFlow {
        val listener = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }
        }

        registerNetworkCallback(networkRequest, listener)
        awaitClose { unregisterNetworkCallback(listener) }
    }
}