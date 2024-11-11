package com.example.spacexlauches.Variant1
import com.example.spacexlaunches.Launch
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi2 {

    @GET("/launches/latest")
    fun getLaunches(): Response<List<Launch>>
}