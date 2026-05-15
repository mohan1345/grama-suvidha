package com.gramasuvidha.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gramasuvidha.data.local.dao.FeedbackDao
import com.gramasuvidha.data.local.dao.IssueDao
import com.gramasuvidha.data.local.dao.ProjectDao
import com.gramasuvidha.data.local.dao.UserDao
import com.gramasuvidha.data.local.entity.FeedbackEntity
import com.gramasuvidha.data.local.entity.IssueEntity
import com.gramasuvidha.data.local.entity.ProjectEntity
import com.gramasuvidha.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class, ProjectEntity::class, FeedbackEntity::class, IssueEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GramaSuvidhaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun projectDao(): ProjectDao
    abstract fun feedbackDao(): FeedbackDao
    abstract fun issueDao(): IssueDao
}
