package com.example.s8132684assignment2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8132684assignment2.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _keypass = MutableLiveData<String>()
    val keypass: LiveData<String> = _keypass

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(location: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(location, username, password)
                _keypass.postValue(response.keypass)
            } catch (e: Exception) {
                _error.postValue("Login failed: ${e.localizedMessage}")
            }
        }
    }
}