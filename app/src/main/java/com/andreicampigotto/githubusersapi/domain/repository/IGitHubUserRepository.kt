package com.andreicampigotto.githubusersapi.domain.repository

import com.andreicampigotto.githubusersapi.data.repository.State
import com.andreicampigotto.githubusersapi.domain.model.UserModel

interface IGitHubUserRepository {
    suspend fun getUserInfo(userName: String): State<UserModel>
}
