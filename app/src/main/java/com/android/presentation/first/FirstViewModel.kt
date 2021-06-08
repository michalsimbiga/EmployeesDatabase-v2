package com.android.presentation.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.models.User
import com.domain.usecases.GetUsersUseCase
import javax.inject.Inject

class FirstViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase
) : ViewModel() {

//    private val _users = MutableLiveData<Resource<List<User>>>()
//    val users: LiveData<Resource<List<User>>> = _users
//
//    fun getUsers() {
//        getUsers(
//            viewModelScope, None,
//            ::onGetUsernameSuccess,
//            ::onGetUsernameError
//        )
//        _users.value = Resource.Loading()
//    }
//
//    private fun onGetUsernameSuccess(users: List<User>) {
//        val stringBuilder = StringBuilder()
//        for (user in users) {
//            stringBuilder.append("${user.name.first}, ")
//        }
//        _users.value = Resource.Success(users)
//    }
//
//    private fun onGetUsernameError(error: Throwable) {
//        _users.value = Resource.Error(error)
//    }
}