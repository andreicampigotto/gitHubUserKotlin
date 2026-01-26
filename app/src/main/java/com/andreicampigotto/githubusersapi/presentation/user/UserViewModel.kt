package com.andreicampigotto.githubusersapi.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreicampigotto.githubusersapi.domain.model.UserModel
import com.andreicampigotto.githubusersapi.domain.repository.IGitHubUserRepository
import kotlinx.coroutines.launch
import com.andreicampigotto.githubusersapi.data.repository.State



class UserViewModel(
    private val repository: IGitHubUserRepository
) : ViewModel() {

    private val _users = MutableLiveData<State<UserModel>>()
    val users: LiveData<State<UserModel>> = _users

    fun getUserInfo(user: String) {
        _users.postValue(State.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getUserInfo(user)
                _users.postValue(
                    State.Success(
                        (result as State.Success).model)
                )
            } catch (ex: Exception) {
                _users.postValue(State.Error(ex.localizedMessage))
            }
        }
    }
}