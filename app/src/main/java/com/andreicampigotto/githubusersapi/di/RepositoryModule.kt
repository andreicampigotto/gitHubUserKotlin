package com.andreicampigotto.githubusersapi.di

import com.andreicampigotto.githubusersapi.data.repository.GitHubUserRepository
import com.andreicampigotto.githubusersapi.domain.repository.IGitHubUserRepository
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        factory<IGitHubUserRepository> { GitHubUserRepository(get()) }
    }
}