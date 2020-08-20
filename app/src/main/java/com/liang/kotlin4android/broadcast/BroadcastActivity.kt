package com.liang.kotlin4android.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import com.liang.kotlin4android.constant.Constant
import kotlinx.android.synthetic.main.activity_broadcast.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * 创建日期：2020/5/22 on 10:46
 * 描述: 广播接收器
 * 作者: liangyang
 */
class BroadcastActivity : BaseActivity() {

    private lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        title="BroadcastReceiver"

        val nowTime = getNowTime()
        tvTime.text = nowTime

        //Intent.ACTION_TIME_TICK             系统每分钟会发出该广播
        //Intent.ACTION_TIME_CHANGED)         时间改变,例如手动修改设置里的时间
        //Intent.ACTION_TIMEZONE_CHANGED)     例如手动修改设置里的时区
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")  //TIME_TICK广播事件仅仅能动态注冊
        timeChangeReceiver = TimeChangeReceiver()
        //注册广播
        registerReceiver(timeChangeReceiver,intentFilter)

        btnForceOffline.setOnClickListener {
            //发送自定义的强制下线的广播
            val intent = Intent(Constant.FORCE_OFFLINE_BROADCAST)
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }

    }

    override fun isSetTransparencyBar(): Boolean {
        return false
    }


    /**
     * 监听系统时间变化的广播接收器
     */
    inner class TimeChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            toast("Time has changed")
            val nowTime = getNowTime()
            tvTime.text = nowTime
        }

    }

    /**
     * Cofox 日期函数
     * created at 2017/12/19 0:06
     * 功能描述：返回当前日期，格式：2017-12-19 12:13:55
     * file:cofoxFuction.kt
     *
     *
     * 修改历史：
     * 2017/12/19:新建
     *
     */
    @SuppressLint("SimpleDateFormat")
    private fun getNowTime(): String {
//        if (android.os.Build.VERSION.SDK_INT >= 24){
//            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
//        }else{
//            var tms = Calendar.getInstance()
//            return tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString() +":" + tms.get(Calendar.SECOND).toString() +"." + tms.get(Calendar.MILLISECOND).toString()
//        }

        return if (android.os.Build.VERSION.SDK_INT >= 24){
//            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
//            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
            SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())
        }else{
            val tms = Calendar.getInstance()
//            tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString() +":" + tms.get(Calendar.SECOND).toString() +"." + tms.get(Calendar.MILLISECOND).toString()
            tms.get(Calendar.YEAR).toString() + "-" + tms.get(Calendar.MONTH).toString() + "-" + tms.get(Calendar.DAY_OF_MONTH).toString() + " " + tms.get(Calendar.HOUR_OF_DAY).toString() + ":" + tms.get(Calendar.MINUTE).toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //反注册广播接收器
        unregisterReceiver(timeChangeReceiver)
    }
}
