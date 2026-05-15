package com.gramasuvidha.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val budget: String,
    val progressPercentage: Int,
    val status: String, // "Ongoing", "Completed", "Pending"
    val startDate: String,
    val endDate: String,
    val beforeImage: String,
    val afterImage: String
)
