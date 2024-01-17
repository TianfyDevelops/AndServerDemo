package com.example.sendserverdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


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

            val response =
                AccessServerManager.instance.request(baseRequest, BaseResponse<String>().javaClass)

            Log.d("viewModel", response.toString())

        }

    }


}