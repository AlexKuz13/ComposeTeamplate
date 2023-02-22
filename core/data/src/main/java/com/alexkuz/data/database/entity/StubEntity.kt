package com.alexkuz.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StubEntity(
    @PrimaryKey
    val id: Long,
    val stubField: String,
)