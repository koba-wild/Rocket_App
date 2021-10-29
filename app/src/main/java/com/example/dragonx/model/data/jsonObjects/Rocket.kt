package com.example.dragonx.model.data.jsonObjects

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "rockets_table")
@Parcelize
data class Rocket (
    @SerializedName("id") @PrimaryKey val id: String,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("wikipedia") val wikipedia: String?,
    @Embedded
    @SerializedName("diameter") val diameter: Diameter,
    @SerializedName("dry_mass_kg") val massKg: Int?,
    @SerializedName("dry_mass_lb") val massLb: Int?,
    @SerializedName("first_flight") val firstFlight: String?,
    @SerializedName("flickr_images") val flickrImages: ArrayList<String>?
) : Parcelable
