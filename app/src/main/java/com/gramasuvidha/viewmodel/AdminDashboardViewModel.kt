package com.gramasuvidha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gramasuvidha.data.repository.IssueRepository
import com.gramasuvidha.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AdminDashboardViewModel @Inject constructor(
    projectRepository: ProjectRepository,
    issueRepository: IssueRepository
) : ViewModel() {

    val totalProjects: StateFlow<Int> = projectRepository.getTotalProjectsCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val ongoingProjects: StateFlow<Int> = projectRepository.getOngoingProjectsCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val completedProjects: StateFlow<Int> = projectRepository.getCompletedProjectsCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val totalIssues: StateFlow<Int> = issueRepository.getTotalIssuesCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)
}
