package com.gramasuvidha.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gramasuvidha.data.local.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE id = :id LIMIT 1")
    fun getProjectById(id: String): Flow<ProjectEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: ProjectEntity)

    @Update
    suspend fun updateProject(project: ProjectEntity)

    @Delete
    suspend fun deleteProject(project: ProjectEntity)

    @Query("SELECT COUNT(*) FROM projects")
    fun getTotalProjectsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM projects WHERE status = 'Ongoing'")
    fun getOngoingProjectsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM projects WHERE status = 'Completed'")
    fun getCompletedProjectsCount(): Flow<Int>
}
