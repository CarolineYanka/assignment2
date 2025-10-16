package com.example.s8132684assignment2.repository

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.network.ApiService // Make sure ApiService is imported
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getDashboard(keypass: String): DashboardResponse {
        // This line calls the function defined in your ApiService interface
        return apiService.getDashboard(keypass)
    }
}
