package com.liang.kotlin4android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.liang.kotlin4android.constant.Constant
import com.liang.kotlin4android.utils.ActivityBox
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

/**
 * 创建日期:2020/8/4 on 7:11 PM
 * 描述: 继承与实现 在kotlin中，任何一个非抽象类默认都是不可以被继承的，相当于Java中给类声明了final关键字
 * 在BaseActivity类前面加open关键字--->抽象类--->使之可以被继承
 * 作者: 杨亮
 */
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Kotlin中的javaClass表示获取当前实例的Class对象
        Log.d("BaseActivity", javaClass.simpleName)
        ActivityBox.addActivity(this)

        if (isSetTransparencyBar()) {
            setTransparencyBar()
        }
    }

    protected abstract fun isSetTransparencyBar(): Boolean

    private fun setTransparencyBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //全透明状态栏(不带阴影)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        } else {
            //半透明状态栏(带阴影)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
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
