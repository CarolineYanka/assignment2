package com.example.s8132684assignment2.repository

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.network.ApiService
import jakarta.inject.Inject

class DashboardRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getDashboard(keypass: String): DashboardResponse {
        return apiService.getDashboard(keypass)
    }
}