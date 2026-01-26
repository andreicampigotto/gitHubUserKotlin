package com.andreicampigotto.githubusersapi.data.remote.api

import com.andreicampigotto.githubusersapi.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IGitHubUserApi {

    @GET("users/{userName}")
    suspend fun getUserInfo(
        @Path("userName")
        userName: String
    ): UserResponse
}