package com.example.spacexlauches.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpaceXApiProvider(private val okHttpClient: OkHttpClient) {
    val provide: SpaceXApiService get() =
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v5/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
            .create(SpaceXApiService::class.java)
}