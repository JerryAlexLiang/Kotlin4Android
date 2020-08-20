package com.liang.kotlin4android.jetpack.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * 创建日期: 2020/8/20 on 5:09 PM
 * 描述: WorkManager的基本用法
 * 1、定义一个后台任务，并实现具体的任务逻辑；
 * 2、配置该后台任务的运行条件和约束信息，并构建后台任务请求；
 * 3、将该后台任务请求传入WorkManager的enqueue()方法中，系统会在合适的时间运行。
 * 作者: 杨亮
 */
class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        private const val TAG = "SimpleWorker"
    }

    /**
     * 1、定义一个后台任务，并实现具体的任务逻辑
     * 后台任务的写法非常固定，也很好理解
     * （1）、首先，每一个后台任务都必须继承自Worker类，并调用它唯一的构造函数；
     * （2）、然后，重写父类中的doWork()方法，在这个方法中编写具体的后台任务逻辑即可；
     * （3）、doWork()方法不会运行在主线程当中，因此可以放心的在这里执行耗时逻辑；
     * （4）、doWork()方法要求返回一个Result对象，用于表示任务的运行结果，成功就返回Result.success()，失败就返回Result.failure(),
     * 除此之外，还有一个Result.retry()方法，也代表着失败，只是可以结合WorkRequest.Builder的setBackoffCriteria()方法来重新执行任务；
     */
    override fun doWork(): Result {
        Log.d(TAG, "do Work in SimpleWorker")
        return Result.success()
    }
}