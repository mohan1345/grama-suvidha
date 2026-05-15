package com.gramasuvidha.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gramasuvidha.R
import com.gramasuvidha.ui.components.CustomButton
import com.gramasuvidha.ui.components.CustomTextField
import com.gramasuvidha.viewmodel.AuthState
import com.gramasuvidha.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("USER") } // "USER" or "ADMIN"
    
    val authState by viewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                Toast.makeText(context, "Registration Successful. Please login.", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
                onNavigateToLogin()
            }
            is AuthState.Error -> {
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.register),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = stringResource(id = R.string.name)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(id = R.string.email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(id = R.string.password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.role), style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedRole == "USER",
                onClick = { selectedRole = "USER" }
            )
            Text(stringResource(id = R.string.citizen), modifier = Modifier.clickable { selectedRole = "USER" })
            
            Spacer(modifier = Modifier.padding(16.dp))
            
            RadioButton(
                selected = selectedRole == "ADMIN",
                onClick = { selectedRole = "ADMIN" }
            )
            Text(stringResource(id = R.string.admin), modifier = Modifier.clickable { selectedRole = "ADMIN" })
        }

        Spacer(modifier = Modifier.height(32.dp))
        
        CustomButton(
            text = stringResource(id = R.string.register),
            onClick = { viewModel.register(name, email, password, selectedRole) },
            enabled = name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && authState != AuthState.Loading
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Already have an account? ${stringResource(id = R.string.login)}",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onNavigateToLogin)
        )
    }
}
