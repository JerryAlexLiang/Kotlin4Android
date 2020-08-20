package com.liang.kotlin4android.jetpack.workManager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.observe
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.concurrent.TimeUnit

/**
 * 创建日期:2020/8/20 on 5:22 PM
 * 描述: WorkManager
 * 1、WorkManager很适合用于处理一些要求定时执行的任务；
 * 2、支持周期性任务、链式任务处理等功能；
 * 3、要明确：WorkManager和Service并不相同，也没有直接的联系；
 * （1）、Service是四大组件之一，它在没有被销毁的请款修改是一直保持在后台运行的；
 * （2）、WorkManager只是一个处理定时任务的工具，它可以保证即使在应用退出甚至手机重启的情况下，之前注册的任务仍然将会得到执行，
 * 因此WorkManager很适合用于执行一些定期和服务器进行交互的任务，比如周期性地同步数据等等；
 * 另外，使用WorkManager注册的周期性任务不能保证一定会准时执行，是系统为了减少电量消耗，不是bug；
 *
 * 作者: 杨亮
 */
class WorkManagerActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, WorkManagerActivity::class.java)
            context.startActivity(intent)
        }

        private const val TAG = "SimpleWorker"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)

        title = "JetPack - WorkManager定时任务工具"

        //2、WorkManager用法步骤2
        //配置该后台任务的运行条件和约束信息，并构建后台任务请求；
        //OneTimeWorkRequest.Builder是WorkRequest.Builder的子类，用于构建单次运行的后台任务请求；
//            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()

        //让后台任务在指定的延迟时间后运行
        val request =
            OneTimeWorkRequest
                .Builder(SimpleWorker::class.java)
                .addTag("simple") //给后台任务请求添加标签
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

        btnDoWork.setOnClickListener {
            //WorkRequest.Builder还有另一个子类PeriodicWorkRequest.Builder，可用于构建周期性运行的后台任务请求，但是为了降低设备性能消耗，其构造函数中传入的运行周期间隔不能短于15分钟
            val request2 =
                PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()

            //3、WorkManager用法步骤3
            //将该后台任务请求传入WorkManager的enqueue()方法中，系统会在合适的时间运行该后台任务；
            WorkManager.getInstance(this).enqueue(request)

        }

        btnCancelWork.setOnClickListener {
            //根据后台任务标签取消后台任务请求，使用标签，可以将统一标签名的所有后台任务请求全部取消
            WorkManager.getInstance(this).cancelAllWorkByTag("simple")

            //没有标签，也可以通过id来取消后台任务请求,但是id只能取消的那个后台任务请求
//            WorkManager.getInstance(this).cancelWorkById(request.id)

            //一次性取消所有后台任务请求
//            WorkManager.getInstance(this).cancelAllWork()
        }

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(request.id)
            .observe(this) { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Log.d(TAG, "do work succeeded")
                } else if (workInfo.state == WorkInfo.State.FAILED) {
                    Log.d(TAG, "do work failed")
                } else if (workInfo.state == WorkInfo.State.CANCELLED) {
                    Log.d(TAG, "do work cancelled")
                }
            }

        //WorkManager-链式任务 (伪代码)
//        val syns = ""
//        val compress = ""
//        val upload = ""
//
//        WorkManager.getInstance(this)
//            .beginWith(syns)
//            .then(compress)
//            .then(upload)
//            .enqueue(request)
    }

    override fun isSetTransparencyBar(): Boolean {
        return false
    }
}