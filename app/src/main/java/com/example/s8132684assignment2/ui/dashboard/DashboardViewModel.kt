package com.example.s8132684assignment2.ui.dashboard

import androidx.lifecycle.*
import com.example.s8132684assignment2.data.Entity
import com.example.s8132684assignment2.data.EntityRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: EntityRepository) : ViewModel() {

    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> get() = _entities

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loadDashboardData() {
        viewModelScope.launch {
            val result = repository.fetchEntities()
            result.onSuccess { list ->
                _entities.value = list
            }.onFailure { e ->
                _error.value = e.message
            }
        }
    }
}
