package com.example.sendserverdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun getUserInfo() {
        viewModelScope.launch {
            val userInfoRequest = UserInfoRequest()
            val response = userInfoRequest.requestGet()
            if (response.isSuccess) {
                Log.d("viewModel", response.data.toString())
            } else {
                Log.d("viewModel", response.message)
            }
        }
    }


    fun setUserInfo() {
        viewModelScope.launch {
            val userInfoRequest = UserInfoRequest()
            val result = userInfoRequest.requestPost()
        }

    }


}