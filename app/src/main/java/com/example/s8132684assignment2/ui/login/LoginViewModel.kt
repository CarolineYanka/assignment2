package com.example.s8132684assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8132684assignment2.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                _loginState.value = LoginUiState.Loading

                val response = loginRepository.login(username, password)

                // Handle response: store keypass
                if (response.keypass != null) {
                    _loginState.value = LoginUiState.Success(response.keypass)
                } else {
                    _loginState.value = LoginUiState.Error("Invalid response from server")
                }

            } catch (e: Exception) {
                _loginState.value = LoginUiState.Error(e.message ?: "Login failed")
            }
        }
    }
}

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val keypass: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
