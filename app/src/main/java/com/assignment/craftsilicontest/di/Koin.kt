package com.assignment.craftsilicontest.di

import com.assignment.craftsilicontest.domain.ApiService
import com.assignment.craftsilicontest.domain.httpClient
import com.assignment.craftsilicontest.presentation.mainModule
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

fun initKoin(){
    stopKoin()
    startKoin {
        modules(networkModule)
        modules(mainModule)
    }
}

val networkModule = module {
    single { provideApiService(get()) }
    single { provideHttpClient() }
}

fun provideHttpClient(): HttpClient {
    return httpClient
}

fun provideApiService(httpClient: HttpClient): ApiService {
    return ApiService(httpClient)
}