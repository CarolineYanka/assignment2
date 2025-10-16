package com.example.s8132684assignment2.network

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.data.LoginResponse
import com.vu.s8132684assignment2.data.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Login endpoint (replace 'footscray' with your class location if needed)
    @POST("footscray/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    // Dashboard endpoint
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
}
