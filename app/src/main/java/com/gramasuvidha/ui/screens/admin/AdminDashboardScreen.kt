package com.gramasuvidha.ui.screens.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gramasuvidha.R
import com.gramasuvidha.ui.components.CustomButton
import com.gramasuvidha.viewmodel.AdminDashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onNavigateToManageProjects: () -> Unit,
    onNavigateToManageFeedbacks: () -> Unit,
    onNavigateToManageIssues: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: AdminDashboardViewModel = hiltViewModel()
) {
    val totalProjects by viewModel.totalProjects.collectAsState()
    val ongoingProjects by viewModel.ongoingProjects.collectAsState()
    val completedProjects by viewModel.completedProjects.collectAsState()
    val totalIssues by viewModel.totalIssues.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.dashboard)) },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                AnalyticsCard(title = "Total", count = totalProjects, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.padding(8.dp))
                AnalyticsCard(title = "Ongoing", count = ongoingProjects, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                AnalyticsCard(title = "Completed", count = completedProjects, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.padding(8.dp))
                AnalyticsCard(title = "Issues", count = totalIssues, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(32.dp))
            CustomButton(text = stringResource(id = R.string.manage_projects), onClick = onNavigateToManageProjects)
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(text = "Manage Feedbacks", onClick = onNavigateToManageFeedbacks)
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(text = "Manage Issues", onClick = onNavigateToManageIssues)
        }
    }
}

@Composable
fun AnalyticsCard(title: String, count: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = count.toString(), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}
