package com.liang.kotlin4android.jetpack.liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 创建日期: 2020/8/19 on 4:47 PM
 * 描述: 前面所有的内容都有一个前提：LiveData对象的实例都是在ViewModel中创建的，在实际项目中，很有可能ViewModel中的某个LiveData对象是调用另外的方法获取的
 * 新建一个object单例类
 * 作者: 杨亮
 */
object Repository {

    /**
     * 模拟实际场景：根据传入的userId参数去服务器请求或者到数据库查找相应的User对象
     * 这里的getUser(userId)方法返回的是一个包含Use数据的LiveData对象，
     * 每次调用getUser()方法都会返回一个新的LiveData实例
     */
    fun getUser(userId: String): LiveData<User> {
        val mutableLiveData = MutableLiveData<User>()
        mutableLiveData.value = User(userId, userId, 0)
        return mutableLiveData
    }
}