package com.gramasuvidha.data.repository

import com.gramasuvidha.data.local.dao.IssueDao
import com.gramasuvidha.data.local.entity.IssueEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IssueRepository @Inject constructor(
    private val issueDao: IssueDao
) {
    fun getAllIssues(): Flow<List<IssueEntity>> = issueDao.getAllIssues()

    fun getIssuesForProject(projectId: String): Flow<List<IssueEntity>> = issueDao.getIssuesForProject(projectId)

    suspend fun insertIssue(issue: IssueEntity) {
        issueDao.insertIssue(issue)
    }

    suspend fun updateIssue(issue: IssueEntity) {
        issueDao.updateIssue(issue)
    }

    fun getTotalIssuesCount() = issueDao.getTotalIssuesCount()
}
