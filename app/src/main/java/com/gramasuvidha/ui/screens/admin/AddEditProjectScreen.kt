package com.gramasuvidha.ui.screens.admin

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.gramasuvidha.data.local.entity.ProjectEntity
import com.gramasuvidha.ui.components.CustomButton
import com.gramasuvidha.ui.components.CustomTextField
import com.gramasuvidha.viewmodel.ManageProjectsViewModel
import com.gramasuvidha.viewmodel.ProjectDetailViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditProjectScreen(
    projectId: String?,
    onNavigateBack: () -> Unit,
    manageViewModel: ManageProjectsViewModel = hiltViewModel(),
    detailViewModel: ProjectDetailViewModel? = if (projectId != null) hiltViewModel() else null
) {
    val context = LocalContext.current
    val projectToEdit by detailViewModel?.project?.collectAsState() ?: remember { mutableStateOf(null) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var progress by remember { mutableStateOf(0f) }
    var status by remember { mutableStateOf("Pending") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var beforeImage by remember { mutableStateOf("") }
    var afterImage by remember { mutableStateOf("") }

    LaunchedEffect(projectToEdit) {
        projectToEdit?.let {
            title = it.title
            description = it.description
            budget = it.budget
            progress = it.progressPercentage.toFloat()
            status = it.status
            startDate = it.startDate
            endDate = it.endDate
            beforeImage = it.beforeImage
            afterImage = it.afterImage
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (projectId == null) stringResource(id = R.string.add_project) else "Edit Project") },
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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            CustomTextField(value = title, onValueChange = { title = it }, label = stringResource(id = R.string.title))
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(value = description, onValueChange = { description = it }, label = stringResource(id = R.string.description), maxLines = 4, singleLine = false)
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(value = budget, onValueChange = { budget = it }, label = "Budget (e.g., 5,00,000)")
            Spacer(modifier = Modifier.height(12.dp))

            Text("Progress: ${progress.toInt()}%")
            Slider(value = progress, onValueChange = { progress = it }, valueRange = 0f..100f)
            Spacer(modifier = Modifier.height(12.dp))

            CustomTextField(value = status, onValueChange = { status = it }, label = "Status (Ongoing, Completed, Pending)")
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(value = startDate, onValueChange = { startDate = it }, label = stringResource(id = R.string.start_date))
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(value = endDate, onValueChange = { endDate = it }, label = stringResource(id = R.string.end_date))
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(value = beforeImage, onValueChange = { beforeImage = it }, label = stringResource(id = R.string.before_image))
            Spacer(modifier = Modifier.height(12.dp))
            CustomTextField(value = afterImage, onValueChange = { afterImage = it }, label = stringResource(id = R.string.after_image))
            
            Spacer(modifier = Modifier.height(24.dp))
            CustomButton(
                text = stringResource(id = R.string.save),
                onClick = {
                    val p = ProjectEntity(
                        id = projectId ?: UUID.randomUUID().toString(),
                        title = title,
                        description = description,
                        budget = budget,
                        progressPercentage = progress.toInt(),
                        status = status,
                        startDate = startDate,
                        endDate = endDate,
                        beforeImage = beforeImage,
                        afterImage = afterImage
                    )
                    manageViewModel.addOrUpdateProject(p)
                    Toast.makeText(context, "Project Saved!", Toast.LENGTH_SHORT).show()
                    onNavigateBack()
                },
                enabled = title.isNotBlank() && description.isNotBlank()
            )
            
            if (projectId != null) {
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    text = stringResource(id = R.string.delete),
                    onClick = {
                        projectToEdit?.let {
                            manageViewModel.deleteProject(it)
                            Toast.makeText(context, "Project Deleted!", Toast.LENGTH_SHORT).show()
                            onNavigateBack()
                        }
                    }
                )
            }
        }
    }
}
