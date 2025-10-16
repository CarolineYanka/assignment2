package com.example.s8132684assignment2.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8132684assignment2.data.Entity
import com.example.s8132684assignment2.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> = _entities

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDashboard(keypass)
                _entities.postValue(response.entities)
            } catch (e: Exception) {
                _error.postValue("Failed to load dashboard: ${e.localizedMessage}")
            }
        }
    }
}