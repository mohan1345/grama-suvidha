package com.gramasuvidha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gramasuvidha.data.local.entity.FeedbackEntity
import com.gramasuvidha.data.local.entity.IssueEntity
import com.gramasuvidha.data.repository.FeedbackRepository
import com.gramasuvidha.data.repository.IssueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackIssueViewModel @Inject constructor(
    private val feedbackRepository: FeedbackRepository,
    private val issueRepository: IssueRepository
) : ViewModel() {

    val allFeedbacks: StateFlow<List<FeedbackEntity>> = feedbackRepository.getAllFeedbacks()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val allIssues: StateFlow<List<IssueEntity>> = issueRepository.getAllIssues()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun submitFeedback(feedback: FeedbackEntity) {
        viewModelScope.launch {
            feedbackRepository.insertFeedback(feedback)
        }
    }

    fun submitIssue(issue: IssueEntity) {
        viewModelScope.launch {
            issueRepository.insertIssue(issue)
        }
    }

    fun updateIssueStatus(issue: IssueEntity, newStatus: String) {
        viewModelScope.launch {
            issueRepository.updateIssue(issue.copy(status = newStatus))
        }
    }
}
