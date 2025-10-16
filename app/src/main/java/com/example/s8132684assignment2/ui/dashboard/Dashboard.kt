package com.example.s8132684assignment2.ui.dashboard

import com.example.s8132684assignment2.data.DashboardResponse
import retrofit2.http.GET
import retrofit2.http.Path
interface ApiService {
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
}
    