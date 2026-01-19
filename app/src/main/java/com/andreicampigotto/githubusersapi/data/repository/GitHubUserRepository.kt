package com.andreicampigotto.githubusersapi.data.repository

import com.andreicampigotto.githubusersapi.data.remote.api.IGitHubApiUserApi
import com.andreicampigotto.githubusersapi.domain.mapper.asDomainModel
import com.andreicampigotto.githubusersapi.domain.model.UserModel
import com.andreicampigotto.githubusersapi.domain.repository.IGitHubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<out T>(val model: T) : State<T>()
    data class Error(val message: String?) : State<Nothing>()
}

class GitHubUserRepository(
    private val iGitHubApiUserApi: IGitHubApiUserApi,
): IGitHubUserRepository {
    override suspend fun getUserInfo(userName: String): State<UserModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = iGitHubApiUserApi.getUserInfo(userName)
                State.Success(response.asDomainModel())
            } catch (ex: IOException) {
                // Network Error
                State.Error(message = ex.localizedMessage)
            } catch (ex: Exception) {
                State.Error(message = ex.localizedMessage)
            }
        }
    }
}