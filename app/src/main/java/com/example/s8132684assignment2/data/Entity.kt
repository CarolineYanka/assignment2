package com.example.s8132684assignment2.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Entity(
    val property1: String,
    val property2: String,
    val description: String
) : Parcelable
