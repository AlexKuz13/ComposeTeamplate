package com.alexkuz.data.resources

import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes id: Int): String

    fun getString(@StringRes id: Int, vararg args: Any): String

    fun getPlural(@PluralsRes id: Int, quantity: Int): String

    fun getPlural(@PluralsRes id: Int, quantity: Int, vararg args: Any): String

    fun getColor(@ColorRes res: Int): Int
}