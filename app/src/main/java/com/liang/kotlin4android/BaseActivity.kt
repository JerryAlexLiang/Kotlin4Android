package com.liang.kotlin4android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.liang.kotlin4android.constant.Constant
import com.liang.kotlin4android.utils.ActivityBox
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

open class BaseActivity : AppCompatActivity() {

    private lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity", javaClass.simpleName)
        ActivityBox.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        //注册自定义的强制下线的广播接收器
        val intentFilter = IntentFilter()
//        intentFilter.addAction("com.liang.kotlin4android.FORCE_OFFLINE")
        intentFilter.addAction(Constant.FORCE_OFFLINE_BROADCAST)
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        //反注册广播接收器
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityBox.removeActivity(this)
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //使用Anko库 - Dialog
            alert("您已被强制下线，请重新登录~", "提示") {
                positiveButton("确定") {
                    ActivityBox.finishAll()             //销毁所有的Activity
                    startActivity<LoginActivity>()     //重新启动LoginActivity
                }
                isCancelable = false
                show()
            }

        }

    }
}
