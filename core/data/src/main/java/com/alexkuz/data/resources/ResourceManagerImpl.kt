package com.alexkuz.data.resources

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager {

    override fun getString(id: Int): String {
        return context.resources.getString(id)
    }

    override fun getString(id: Int, vararg args: Any): String {
        return context.resources.getString(id, *args)
    }

    override fun getPlural(id: Int, quantity: Int): String {
        return context.resources.getQuantityString(id, quantity)
    }

    override fun getPlural(id: Int, quantity: Int, vararg args: Any): String {
        return context.resources.getQuantityString(id, quantity, *args)
    }

    override fun getColor(res: Int): Int {
        return ContextCompat.getColor(context, res)
    }
}