package com.example.spacexlaunches

data class Launch(
    val name: String,
    val date_utc: String,
    val rocket: String,
    val details: String?
)