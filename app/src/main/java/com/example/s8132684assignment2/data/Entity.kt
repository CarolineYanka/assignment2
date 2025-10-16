package com.example.s8132684assignment2.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Entity(
    val exerciseName: String,
    val muscleGroup: String,
    val equipment: String,
    val difficulty: String,
    val caloriesBurnedPerHour: Int,
    val description: String
) : Serializable
