package com.example.s8132684assignment2.ui.dashboard

import retrofit2.http.GET
import retrofit2.http.Path

@GET("dashboard/{keypass}")
suspend fun getDashboard(@Path("keypass") keypass: String): DashboardResponse
