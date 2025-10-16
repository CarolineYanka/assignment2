package com.example.s8132684assignment2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8132684assignment2.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginResult {
    data class Success(val keypass: String): LoginResult()
    data class Error(val errorMessage: String): LoginResult()
}

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository): ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                _loginResult.postValue(LoginResult.Success(response.keypass))
            } catch (e: Exception) {
                _loginResult.postValue(LoginResult.Error("Login failed: ${e.localizedMessage}"))
            }
        }
    }
}