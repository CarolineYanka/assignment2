package com.example.s8132684assignment2.data

import com.squareup.moshi.Json

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)