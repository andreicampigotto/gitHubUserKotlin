package com.andreicampigotto.githubusersapi.di

import com.andreicampigotto.githubusersapi.data.network.HTTPClient
import com.andreicampigotto.githubusersapi.data.remote.api.IGitHubUserApi
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        single { HTTPClient() }
        factory { get<HTTPClient>().create(IGitHubUserApi::class)}
    }
}
