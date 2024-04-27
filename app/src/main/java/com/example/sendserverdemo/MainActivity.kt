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
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
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

        launchOnStart {
            viewModel.userInfoFlow.collect {
                if (it.isSuccess) {
                    Log.d(TAG, "getUserInfo:${it.getOrNull()}")
                } else {
                    Log.d(TAG, "getUserInfo:请求失败")
                }
            }
        }
        launchOnStart {
            viewModel.userInfoListFlow.collect {
                if (it.isSuccess) {
                    Log.d(TAG, "userInfoListFlow:${it.getOrNull()}")
                } else {
                    Log.d(TAG, "userInfoListFlow:请求失败 ${it.exceptionOrNull()?.message}")
                }
            }
        }
        launchOnStart {
            viewModel.setUserInfoFlow.collect {
                if (it.isSuccess) {
                    Log.d(TAG, "setUserInfoFlow:${it.getOrNull()}")
                } else {
                    Log.d(TAG, "setUserInfoFlow:请求失败")
                }
            }
        }
    }


    private fun launchOnStart(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                block.invoke(this)
            }
        }
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
                viewModel.getUserInfo()
            }

            R.id.btn_set_user_info -> {
                viewModel.setUserInfo()
            }

            R.id.btn_get_user_infos -> {
                viewModel.getUserInfoList()
            }
        }
    }


}