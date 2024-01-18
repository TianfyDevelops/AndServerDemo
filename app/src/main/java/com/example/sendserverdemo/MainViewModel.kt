package com.example.sendserverdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcst.retrofit.AccessServerManager
import com.kcst.retrofit.BaseRequest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    fun getUserInfo() {
        viewModelScope.launch {
            val baseRequest = BaseRequest().apply {
                setPath("/user/userInfo")
                setRequestType(BaseRequest.RequestType.GET)
            }
            val response = AccessServerManager.INSTANCE.request(
                baseRequest,
                UserInfoResponse().javaClass
            )
            Log.d("viewModel", response.data.toString())
        }
    }


    fun setUserInfo() {
        viewModelScope.launch {

            val map = HashMap<String, String>()
            map["userId"] = "123"
            map["userName"] = "tianfy"
            val baseRequest = BaseRequest().apply {
                setPath("/user/userInfo")
                setRequestParams(map)
                setRequestType(BaseRequest.RequestType.POST)
            }

            val response = AccessServerManager.INSTANCE.request(
                baseRequest,
                UserInfoResponse().javaClass
            )
            Log.d("viewModel", response.toString())

        }

    }


}