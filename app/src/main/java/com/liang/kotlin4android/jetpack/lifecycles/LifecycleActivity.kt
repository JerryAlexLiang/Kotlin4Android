package com.liang.kotlin4android.jetpack.lifecycles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_lifecycle.*

/**
 * 创建日期:2020/8/19 on 10:55 AM
 * 描述: JetPack - LifeCycles
 * 作者: 杨亮
 */
class LifecycleActivity : BaseActivity() {

    companion object {

        fun actionStart(context: Context) {
            val intent = Intent(context, LifecycleActivity::class.java)
            context.startActivity(intent)
        }

        private const val TAG = "LifecycleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        title = "JetPack组件集合-LifeCycles组件"

        //通过LifecycleOwner（监听生命周期变化），通知给监听器MyObserver
//        lifecycle.addObserver(MyObserver())
        lifecycle.addObserver(MyObserver(lifecycle))

//        tvLifeObserver.text = MyObserver(lifecycle).toString()

        //lifecycle.currentState返回的生命周期状态是一个枚举类型，一共有 DESTROYED, INITIALIZED, CREATED, STARTED, RESUMED这5种状态类型
        //与Activity的生命周期回调所对应的关系是：
        //eg: 当获取的状态是CREATED的时候，说明onCreate()方法已经执行了，但是onStart()方法还没有执行；
        // 当获取的生命周期状态是STARTED的时候，说明onStart()方法已经执行了，但是onResume()方法还没有执行，以此类推
        btnGetCurrentState.setOnClickListener {
            val currentState = lifecycle.currentState
            Log.e(TAG, "当前生命周期状态: $currentState")
            tvCurrentState.text = currentState.toString()
        }


    }

    override fun isSetTransparencyBar(): Boolean {
        return false
    }
}