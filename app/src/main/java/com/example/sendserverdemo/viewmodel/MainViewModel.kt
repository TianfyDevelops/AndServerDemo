package com.example.sendserverdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sendserverdemo.repository.MainRepository
import com.example.sendserverdemo.response.UserInfoListResponse
import com.example.sendserverdemo.response.UserInfoResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    private val _userInfoFlow = MutableSharedFlow<Result<UserInfoResponse>>()
    val userInfoFlow :SharedFlow<Result<UserInfoResponse>> = _userInfoFlow

    private val _userInfoListFlow = MutableSharedFlow<Result<UserInfoListResponse>>()
    val userInfoListFlow = _userInfoListFlow

    private val _setUserInfoFlow = MutableSharedFlow<Result<UserInfoResponse>>()
    val setUserInfoFlow :SharedFlow<Result<UserInfoResponse>> = _setUserInfoFlow

     fun getUserInfo(){
         viewModelScope.launch {
             _userInfoFlow.tryEmit(mainRepository.getUserInfo())
         }
    }
     fun getUserInfoList() {
        viewModelScope.launch {
            val userInfoList = mainRepository.getUserInfoList()
            _userInfoListFlow.emit(userInfoList)
        }
    }
     fun setUserInfo() {
         viewModelScope.launch {
             _setUserInfoFlow.tryEmit(mainRepository.setUserInfo())
         }
    }
}
