package com.example.s8132684assignment2.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Entity(
    @Json(name = "property1") val property1: String,
    @Json(name = "property2") val property2: String,
    @Json(name = "description") val description: String
) : Parcelable