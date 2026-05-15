package com.gramasuvidha.ui.screens.citizen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gramasuvidha.R
import com.gramasuvidha.data.local.entity.FeedbackEntity
import com.gramasuvidha.ui.components.CustomButton
import com.gramasuvidha.ui.components.CustomTextField
import com.gramasuvidha.ui.components.RatingBar
import com.gramasuvidha.viewmodel.FeedbackIssueViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    projectId: String,
    onNavigateBack: () -> Unit,
    viewModel: FeedbackIssueViewModel = hiltViewModel()
) {
    var rating by remember { mutableStateOf(0) }
    var comment by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.give_feedback)) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "Rate this project", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            RatingBar(rating = rating, onRatingChanged = { rating = it })
            
            Spacer(modifier = Modifier.height(24.dp))
            CustomTextField(
                value = comment,
                onValueChange = { comment = it },
                label = "Your feedback",
                maxLines = 5,
                singleLine = false,
                modifier = Modifier.height(150.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            CustomButton(
                text = stringResource(id = R.string.submit),
                onClick = {
                    val feedback = FeedbackEntity(
                        projectId = projectId,
                        rating = rating,
                        comment = comment,
                        userName = "Citizen User" // In a real app, fetch from User session
                    )
                    viewModel.submitFeedback(feedback)
                    Toast.makeText(context, "Feedback Submitted!", Toast.LENGTH_SHORT).show()
                    onNavigateBack()
                },
                enabled = rating > 0 && comment.isNotBlank()
            )
        }
    }
}
