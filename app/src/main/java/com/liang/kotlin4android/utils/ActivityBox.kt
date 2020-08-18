package com.liang.kotlin4android.utils

import android.app.Activity

/**
 * 创建日期：2020/5/18 on 17:22
 * 描述: Activity管理器
 * 使用关键字object创建一个单例类 - 这里使用单例类，是因为全局只需要一个Activity集合ActivityBox
 * 作者: liangyang
 */
object ActivityBox {

    //注意：
    //Kotlin中没有直接定义静态方法的关键字，但是提供了一些语法特性来支持类似与静态方法调用的写法

    //1、使用object单例类的写法会将整个类中的所有方法全部变成类似于静态方法的调用方式

    //2、而如果只是希望让类中的某一个方法变成静态方法的调用方式，就可以使用companion object 伴生类

    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }

}