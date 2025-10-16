package com.example.s8132684assignment2.data

import com.squareup.moshi.Json

data class EntityResponse(
    @Json(name = "entities") val entities: List<Entity>,
    @Json(name = "entityTotal") val entityTotal: Int
)