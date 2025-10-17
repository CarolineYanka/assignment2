package com.example.s8132684assignment2.network

import com.example.s8132684assignment2.data.DashboardResponse
import com.example.s8132684assignment2.data.EntityResponse
import com.example.s8132684assignment2.data.LoginResponse
import com.vu.s8132684assignment2.data.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("footscray/auth")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("footscray/{keypass}")
    suspend fun getDashboardData(@Path("keypass") keypass: String): DashboardResponse
}
