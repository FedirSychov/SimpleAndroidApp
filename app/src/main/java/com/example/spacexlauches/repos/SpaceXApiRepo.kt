package com.example.spacexlauches.repos

import android.util.Log
import com.example.spacexlauches.services.SpaceXApiProvider
import com.example.spacexlauches.models.Launch

class SpaceXApiRepo(private val spaceXApiProvider: SpaceXApiProvider) {

    suspend fun getLaunches(): List<Launch> {
        val launches = spaceXApiProvider.provide.getLaunches()
        Log.d("SERVICE", "Launches: $launches")
        return launches
    }
}