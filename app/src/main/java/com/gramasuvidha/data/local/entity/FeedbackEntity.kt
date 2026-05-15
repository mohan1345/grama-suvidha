package com.gramasuvidha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedbacks")
data class FeedbackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val projectId: String,
    val rating: Int,
    val comment: String,
    val userName: String,
    val timestamp: Long = System.currentTimeMillis()
)
