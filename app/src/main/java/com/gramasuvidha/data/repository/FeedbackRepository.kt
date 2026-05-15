package com.gramasuvidha.data.repository

import com.gramasuvidha.data.local.dao.FeedbackDao
import com.gramasuvidha.data.local.entity.FeedbackEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedbackRepository @Inject constructor(
    private val feedbackDao: FeedbackDao
) {
    fun getFeedbacksForProject(projectId: String): Flow<List<FeedbackEntity>> = feedbackDao.getFeedbacksForProject(projectId)
    
    fun getAllFeedbacks(): Flow<List<FeedbackEntity>> = feedbackDao.getAllFeedbacks()

    suspend fun insertFeedback(feedback: FeedbackEntity) {
        feedbackDao.insertFeedback(feedback)
    }
}
