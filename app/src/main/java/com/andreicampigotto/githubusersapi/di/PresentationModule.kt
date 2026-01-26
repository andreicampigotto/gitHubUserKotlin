package com.andreicampigotto.githubusersapi.di

import com.andreicampigotto.githubusersapi.presentation.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel { UserViewModel(get()) }
    }
}