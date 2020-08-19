package com.liang.kotlin4android.jetpack.lifecycles

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 创建日期: 2020/8/19 on 9:35 AM
 * 描述:
 * 1、LifeCycles组件可以让任何一个类都能轻松感知到Activity的生命周期，同时又不需要在Activity中编写大量的逻辑处理
 * 新建MyObserver类，实现LifecycleObserver接口（空方法接口，只需要进行一下接口声明就可以了，而不用重写任何方法）
 *
 * 2、还需要借助LifecycleOwner，当Activity生命周期发生变化的时候，去通知给MyObserver
 * 语法结构： lifecycleOwner.lifecycle.addObserver(MyObserver())
 * 首先调用lifecycleOwner的getLifecycle()方法，得到一个Lifecycle对象，然后调用它的addObserver()方法来观察LifecycleOwner的生命周期，
 * 再把MyObserver的实例传进去，就可以让MyObserver()得到通知了（Activity、Fragment生命周期的变化 - Activity、Fragment本身就是一个LifecycleOwner的实例 - AndroidX库自动完成）
 *
 * 3、MyObserver可以感知到Activity的生命周期发生了变化，但是没有办法主动获知当前的生命周期，解决办法：需要在MyObserver的构造函数中将Lifecycle对象传进来即可
 * 有了lifecycle对象之后，就可以在任何地方调用lifecycle.currentState来主动获知当前的生命周期状态
 * lifecycle.currentState返回的生命周期状态是一个枚举类型（5种状态）
 * 作者: 杨亮
 */
class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    companion object {
        private const val TAG = "MyObserver"
    }

    /**
     * 可以在MyObserver中定义任何方法，但是如果想感知到Activity的生命周期，需要借助注解功能
     */

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityCreate() {
        Log.e(TAG, "activityCreate:  执行onCreate()")
        val currentState = lifecycle.currentState
        Log.e(TAG, "activityCreate:  当前状态: $currentState"  )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.e(TAG, "activityStart: 执行onStart()")
        val currentState = lifecycle.currentState
        Log.e(TAG, "activityStart:  当前状态: $currentState"  )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activityResume() {
        Log.e(TAG, "activityResume: 执行onResume()")
        val currentState = lifecycle.currentState
        Log.e(TAG, "activityResume:  当前状态: $currentState"  )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPause() {
        Log.e(TAG, "activityPause: 执行onPause()")
        val currentState = lifecycle.currentState
        Log.e(TAG, "activityPause:  当前状态: $currentState"  )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.e(TAG, "activityStop: 执行onStop()")
        val currentState = lifecycle.currentState
        Log.e(TAG, "activityStop:  当前状态: $currentState"  )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun activityDestroy() {
        Log.e(TAG, "activityDestroy: 执行onDestroy()")
        val currentState = lifecycle.currentState
        Log.e(TAG, "activityDestroy:  当前状态: $currentState"  )
    }

//    //Lifecycle.Event.ON_ANY 可以匹配Activity的任何生命周期回调
//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun activityRestart() {
//        Log.e(TAG, "activityRestart: ")
//    }


}