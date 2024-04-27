package com.example.sendserverdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sendserverdemo.repository.MainRepository
import com.example.sendserverdemo.response.UserInfoListResponse
import com.example.sendserverdemo.response.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    suspend fun getUserInfo(): StateFlow<Result<UserInfoResponse>> {
        return flow<Result<UserInfoResponse>> {
            mainRepository.getUserInfo()
        }.flowOn(Dispatchers.IO).stateIn(viewModelScope)
    }
    suspend fun getUserInfoList(): StateFlow<Result<UserInfoListResponse>> {
        return flow<Result<UserInfoListResponse>> {
            mainRepository.getUserInfoList()
        }.flowOn(Dispatchers.IO).stateIn(viewModelScope)
    }
    suspend fun setUserInfo(): StateFlow<Result<UserInfoResponse>> {
        return flow<Result<UserInfoResponse>> {
            mainRepository.setUserInfo()
        }.flowOn(Dispatchers.IO).stateIn(viewModelScope)
    }
}
