package com.example.s8132684assignment2.repository

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.network.ApiService
import javax.inject.Inject

class DashboardRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDashboardData(): DashboardResponse {
        return apiService.getDashboardData()
    }
}
