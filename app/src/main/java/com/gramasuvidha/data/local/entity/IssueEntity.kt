package com.gramasuvidha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class IssueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val projectId: String,
    val issueText: String,
    val status: String, // "Pending", "Resolved"
    val reportedBy: String,
    val timestamp: Long = System.currentTimeMillis()
)
