package com.gramasuvidha.data.repository

import com.gramasuvidha.data.local.dao.ProjectDao
import com.gramasuvidha.data.local.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val projectDao: ProjectDao
) {
    fun getAllProjects(): Flow<List<ProjectEntity>> = projectDao.getAllProjects()

    fun getProjectById(id: String): Flow<ProjectEntity?> = projectDao.getProjectById(id)

    suspend fun insertProject(project: ProjectEntity) {
        projectDao.insertProject(project)
    }

    suspend fun updateProject(project: ProjectEntity) {
        projectDao.updateProject(project)
    }

    suspend fun deleteProject(project: ProjectEntity) {
        projectDao.deleteProject(project)
    }

    fun getTotalProjectsCount() = projectDao.getTotalProjectsCount()
    fun getOngoingProjectsCount() = projectDao.getOngoingProjectsCount()
    fun getCompletedProjectsCount() = projectDao.getCompletedProjectsCount()
}
