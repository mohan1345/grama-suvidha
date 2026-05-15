package com.gramasuvidha.ui.screens.citizen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gramasuvidha.R
import com.gramasuvidha.ui.components.CustomButton
import com.gramasuvidha.ui.components.StatusChip
import com.gramasuvidha.viewmodel.ProjectDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectDetailScreen(
    onNavigateBack: () -> Unit,
    onNavigateToFeedback: (String) -> Unit,
    onNavigateToReportIssue: (String) -> Unit,
    viewModel: ProjectDetailViewModel = hiltViewModel()
) {
    val project by viewModel.project.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(project?.title ?: "") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        project?.let { proj ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = proj.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatusChip(status = proj.status)
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = "Budget: ₹${proj.budget}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Duration: ${proj.startDate} to ${proj.endDate}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Progress", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { proj.progressPercentage / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                )
                Text(text = "${proj.progressPercentage}% Completed", style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Description", style = MaterialTheme.typography.titleMedium)
                Text(text = proj.description, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Images", style = MaterialTheme.typography.titleMedium)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text(text = "Before", style = MaterialTheme.typography.bodySmall)
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(proj.beforeImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Before Work Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                            error = painterResource(id = R.drawable.grama_suvidha_logo)
                        )
                    }
                    Column(modifier = Modifier.weight(1f).padding(start = 8.dp)) {
                        Text(text = "After / Current", style = MaterialTheme.typography.bodySmall)
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(proj.afterImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = "After Work Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                            error = painterResource(id = R.drawable.grama_suvidha_logo)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(
                    text = stringResource(id = R.string.give_feedback),
                    onClick = { onNavigateToFeedback(proj.id) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    text = stringResource(id = R.string.report_issue),
                    onClick = { onNavigateToReportIssue(proj.id) }
                )
            }
        }
    }
}
