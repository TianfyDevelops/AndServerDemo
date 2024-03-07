package com.example.sendserverdemo

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kcst.retrofit.RetrofitManager
import com.kcst.sendserver.model.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"
    private lateinit var viewModel: MainViewModel
    private lateinit var retrofit: Retrofit
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
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
                viewModel.getUserInfo()
            }

            R.id.btn_set_user_info -> {
                viewModel.setUserInfo()
            }
            R.id.btn_get_user_infos->{
//                viewModel.getUserInfos()

            }
        }
    }

}