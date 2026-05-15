package com.gramasuvidha.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gramasuvidha.data.local.entity.UserEntity
import com.gramasuvidha.data.repository.AuthRepository
import com.gramasuvidha.datastore.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, passwordHash: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val user = authRepository.login(email, passwordHash)
            if (user != null) {
                sessionManager.saveSession(true, user.role, user.id)
                _authState.value = AuthState.Success(user.role)
            } else {
                _authState.value = AuthState.Error("Invalid credentials")
            }
        }
    }

    fun register(name: String, email: String, passwordHash: String, role: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val existingUser = authRepository.login(email, passwordHash) // Checking if email exists
            if (existingUser != null) {
                _authState.value = AuthState.Error("User already exists")
                return@launch
            }
            val newUser = UserEntity(name = name, email = email, passwordHash = passwordHash, role = role)
            authRepository.register(newUser)
            _authState.value = AuthState.Success(role) // Proceed to login or handle session
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val role: String) : AuthState()
    data class Error(val message: String) : AuthState()
}
