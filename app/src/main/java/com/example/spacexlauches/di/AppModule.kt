package com.example.spacexlauches.di

import com.example.spacexlauches.repos.SpaceXApiRepo
import com.example.spacexlauches.screens.launches.LaunchesViewModel
import com.example.spacexlauches.services.SpaceXApiProvider
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.scope.Scope
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val appModule = module {
    single { generateHttpClient() }
    single { SpaceXApiProvider(get()) }
    single { SpaceXApiRepo(get()) }
    viewModelOf(::LaunchesViewModel)
}

private fun Scope.generateHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        followRedirects(false)
        connectTimeout(10, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
    }.build()
}