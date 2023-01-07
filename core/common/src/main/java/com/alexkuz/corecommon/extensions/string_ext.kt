package com.alexkuz.corecommon.extensions

inline fun <C, R> C.ifNullOrBlank(defaultValue: () -> R?): R? where R : CharSequence, C : R =
    if (isNullOrBlank()) defaultValue() else this