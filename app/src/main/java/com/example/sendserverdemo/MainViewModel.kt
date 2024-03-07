package com.example.sendserverdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcst.retrofit.AccessServerManager
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun getUserInfo() {
        viewModelScope.launch {
            val userInfoRequest = UserInfoGetRequest()
            val response = AccessServerManager.INSTANCE.request(userInfoRequest,UserInfoResponse::class.java)
            if (response.isSuccess) {
                Log.d("viewModel", response.data.toString())
            } else {
                response.errorMsg?.let { Log.d("viewModel", it) }
            }
        }
    }

//    fun getUserInfos() {
//        viewModelScope.launch {
//            val userInfoRequest = UserInfoRequest()
//            val response = userInfoRequest.requestGetUserInfos()
//            if (response.isSuccess) {
//                Log.d("viewModel", response.data.toString())
//            } else {
//                Log.d("viewModel", response.errorMsg)
//            }
//        }
//    }


    fun setUserInfo() {
        viewModelScope.launch {
            val userInfoRequest = UserInfoPostRequest(1,"xiaoming")
            val response = AccessServerManager.INSTANCE.request(userInfoRequest,UserInfoResponse::class.java)
            if (response.isSuccess) {
                Log.d("viewModel", response.data.toString())
            } else {
                response.errorMsg?.let { Log.d("viewModel", it) }
            }
        }
    }


}