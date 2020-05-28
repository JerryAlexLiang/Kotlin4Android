package com.liang.kotlin4android.threadNet

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.annotation.RequiresApi
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_thread.*
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ThreadActivity : BaseActivity() {

    private lateinit var myHandler: MyHandler

    private val flag = 1
    private var mCountNum = 60

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            //在这里可以进行UI操作
            when (msg.what) {
                flag -> {
                    mCountNum--
                    btnStart.text = "倒计时开始"
                    tvTime.text = "剩余: " + mCountNum + "秒"

                    if (mCountNum == 0) {
                        removeCallbacksAndMessages(null)
                        btnStart.isClickable = true
                        btnStart.isFocusable = true
                        mCountNum = 60
                        tvTime.text = "剩余: " + mCountNum + "秒"
                        btnStart.text = "开始"
                    } else {
                        sendEmptyMessageDelayed(flag, 1000)
                    }
                }
            }
        }
    }

    /**
     * 问题分析：
     * 在finish()的时候，Message还没有被处理，Message持有Handler, Handler持有Activity,这样阻止了GC对Acivity的回收，就发生了内存泄露。
     * 正确的写法应该是使用显形的引用，静态内部类与 外部类。使用弱引用WeakReference。
     * 最后在Activity调用onDestroy()的时候要取消掉该Handler对象的Message和Runnable
     */

    //核心代码：
    //
    // private class MyHandler(activity: thisActivity) : Handler() {
    //  private val mActivity: WeakReference<thisActivity> = WeakReference(activity)
    //  override fun handleMessage(msg: Message) {
    //   if (mActivity.get() == null) {
    //    return
    //   }
    //   val activity = mActivity.get()
    //   when (msg.what) {
    //    0-> {
    //    }
    //    else -> {
    //    }
    //   }
    //  }
    // }


    companion object {

        private class MyHandler(activity: ThreadActivity) : Handler() {

            private val mActivity: WeakReference<ThreadActivity> = WeakReference(activity)

            @RequiresApi(Build.VERSION_CODES.M)
            override fun handleMessage(msg: Message) {
//            if (mActivity.get()==null){
//                return
//            }
//            val activity = mActivity.get()

                val activity = mActivity.get() ?: return
                when (msg.what) {
                    activity.flag -> {
                        activity.mCountNum--
                        activity.btnStart.text = "倒计时开始"
                        activity.tvTime.text = "剩余: " + activity.mCountNum + "秒"

                        if (activity.mCountNum == 0) {
                            removeCallbacksAndMessages(null)
                            activity.btnStart.isClickable = true
                            activity.btnStart.isFocusable = true
                            activity.btnStart.setBackgroundColor(activity.getColor(R.color.colorBlue))
                            activity.btnStart.setTextColor(activity.getColor(R.color.colorWhite))
                            activity.mCountNum = 60
                            activity.tvTime.text = "剩余: " + activity.mCountNum + "秒"
                            activity.btnStart.text = "开始"
                        } else {
                            sendEmptyMessageDelayed(activity.flag, 1000)
                        }
                    }
                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        myHandler = MyHandler(this)

        tvTime.text = "剩余: " + mCountNum + "秒"
//        btnStart.setOnClickListener {
//            thread {
//                val msg = Message()
//                msg.what = flag
//                handler.sendMessageDelayed(msg, 1000)
//            }
//            btnStart.isClickable = false
//            btnStart.isFocusable = false
//            btnStart.setBackgroundColor(this.getColor(R.color.colorTextGray))
//            btnStart.setTextColor(this.getColor(R.color.colorBlack))
//        }

        btnStart.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = flag
                myHandler.sendMessageDelayed(msg, 1000)
            }
            btnStart.isClickable = false
            btnStart.isFocusable = false
            btnStart.setBackgroundColor(this.getColor(R.color.colorTextGray))
            btnStart.setTextColor(this.getColor(R.color.colorBlack))
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        handler.removeCallbacksAndMessages(null)
//        btnStart.isClickable = true
//        btnStart.isFocusable = true
//        mCountNum = 60
//    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDestroy() {
        // Remove all Runnable and Message.
        MyHandler(this).removeCallbacksAndMessages(null)
        btnStart.isClickable = true
        btnStart.isFocusable = true
        btnStart.setBackgroundColor(getColor(R.color.colorBlue))
        btnStart.setTextColor(getColor(R.color.colorWhite))
        mCountNum = 60
        super.onDestroy()

    }
}
