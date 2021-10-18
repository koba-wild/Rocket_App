package com.example.dragonx.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RocketDetails(
    var name: String?,
    var description: String?,
    var wikipedia: String?,
    var heightDiameter: Double?,
    var heightFeet: Int?,
    var massKg: Int?,
    var massLb: Int?,
    var firstFlight: String?,
    var flickrImages: List<String>
): Parcelable