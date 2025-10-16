package com.example.s8132684assignment2.repository

import com.example.s8132684assignment2.data.LoginResponse
import com.example.s8132684assignment2.network.ApiService
import com.vu.s8132684assignment2.data.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(location: String, username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return apiService.login(location, request)
    }
}