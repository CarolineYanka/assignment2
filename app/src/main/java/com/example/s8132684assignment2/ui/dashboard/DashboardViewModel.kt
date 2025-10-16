package com.example.s8132684assignment2.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8132684assignment2.data.Entity
import com.example.s8132684assignment2.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    private val _entities = MutableStateFlow<List<Entity>>(emptyList())
    val entities: StateFlow<List<Entity>> = _entities

    fun loadEntities(location: String, keypass: String) {
        viewModelScope.launch {
            try {
                val response = repository.getEntities(location, keypass)
                _entities.value = response.entities
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}