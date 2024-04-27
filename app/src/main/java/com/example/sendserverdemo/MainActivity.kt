package com.example.sendserverdemo

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sendserverdemo.viewmodel.MainViewModel
import com.example.sendserverdemo.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val TAG = "MainActivity"
    }
    private lateinit var viewModel: MainViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        bindServer()
        findViewById<View>(R.id.btn_get_user_info).setOnClickListener(this)
        findViewById<View>(R.id.btn_set_user_info).setOnClickListener(this)
        findViewById<View>(R.id.btn_get_user_infos).setOnClickListener(this)
    }

    private fun bindServer() {
        bindService(Intent(this, MyService::class.java), object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                Log.d(TAG, "--------------service connected-------------")
            }

            override fun onServiceDisconnected(name: ComponentName) {
                Log.d(TAG, "--------------service disconnected-------------")
            }
        }, BIND_AUTO_CREATE)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_get_user_info -> {
                getUserInfo()
            }

            R.id.btn_set_user_info -> {
                setUserInfo()
            }

            R.id.btn_get_user_infos -> {
                getUserInfoList()
            }
        }
    }

    private fun getUserInfoList() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getUserInfoList().collect {
                    if (it.isSuccess){
                        Log.d(TAG,"userInfoList:${it.getOrNull()}")
                    }else{
                        Log.d(TAG,"userInfoList:请求失败")
                    }
                }
            }
        }
    }

    private fun setUserInfo() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.setUserInfo().collect {
                    if (it.isSuccess) {
                        Log.d(TAG,"setUserInfo:${it.getOrNull()}")
                    } else {
                        Log.d(TAG,"setUserInfo:请求失败")
                    }
                }
            }
        }
    }

    private fun getUserInfo() {
        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUserInfo().collect {
                    if (it.isSuccess) {
                        Log.d(TAG,"getUserInfo:${it.getOrNull()}")
                    } else {
                        Log.d(TAG,"getUserInfo:请求失败")
                    }
                }
            }
        }
    }

}