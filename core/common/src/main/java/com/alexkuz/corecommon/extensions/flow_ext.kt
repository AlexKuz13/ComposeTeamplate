package com.alexkuz.corecommon.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Composable
inline fun <reified T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    remember(key1 = this, key2 = lifecycleOwner) {
        onEach(action)
            .flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
    }.collectAsState(initial = this)
}

fun <T> EventChannel() = Channel<T>()