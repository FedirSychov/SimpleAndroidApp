package com.example.spacexlauches.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Launch(
    val auto_update: Boolean? = null,
    val date_local: String? = null,
    val date_precision: String? = null,
    val date_unix: Int? = null,
    val date_utc: String? = null,
    val details: String? = null,
    val flight_number: Int? = null,
    val id: String? = null,
    val launchpad: String? = null,
    val name: String? = null,
    val net: Boolean? = null,
    val payloads: List<String>,
    val rocket: String? = null,
    val static_fire_date_unix: Int? = null,
    val static_fire_date_utc: String? = null,
    val success: Boolean? = null,
    val tbd: Boolean? = null,
    val upcoming: Boolean? = null,
    val window: Int? = null
): Parcelable