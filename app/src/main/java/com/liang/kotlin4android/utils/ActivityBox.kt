package com.liang.kotlin4android.utils

import android.app.Activity

/**
 * 创建日期：2020/5/18 on 17:22
 * 描述: Activity管理器
 * 作者: liangyang
 */
object ActivityBox {

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