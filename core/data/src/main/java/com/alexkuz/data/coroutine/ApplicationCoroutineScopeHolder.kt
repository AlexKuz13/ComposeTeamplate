package com.alexkuz.data.coroutine

import kotlinx.coroutines.CoroutineScope

interface ApplicationCoroutineScopeHolder {
    val applicationScope: CoroutineScope
}