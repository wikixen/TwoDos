package com.wikixen.twodos.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val desc: String,
    val completed: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)