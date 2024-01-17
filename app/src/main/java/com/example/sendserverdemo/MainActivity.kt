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
import androidx.lifecycle.ViewModelLazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"
    private var service: RetrofitService? = null

    //    private MainViewModel mainViewModel;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        bindServer()
        service = RetrofitManager.getRetrofit().create(RetrofitService::class.java)
        findViewById<View>(R.id.btn_get_user_info).setOnClickListener(this)
        findViewById<View>(R.id.btn_set_user_info).setOnClickListener(this)
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
            R.id.btn_get_user_info -> requestUserInfo()
            R.id.btn_set_user_info -> {

                CoroutineScope(SupervisorJob()+Dispatchers.Main).launch{
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
    }



    private fun requestSetUserInfo() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId("1");
//        userInfo.setUserName("tianfy");
        val map = HashMap<String, String>()
        map["userId"] = "123"
        map["userName"] = "tianfy"
        val post = service!!.post("/user/userInfo", map)
        post.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.i(TAG, response.toString())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }

    private fun requestUserInfo() {
        val stringCall = service!![null, "/user/userInfo", null]
        stringCall.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.i(TAG, response.toString())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }
}