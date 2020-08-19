package com.liang.kotlin4android.jetpack.viewModel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProviders
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_counter.*

/**
 * 创建日期:2020/8/18 on 3:51 PM
 * 描述: 计数器Demo - 学习JetPack组件集中的ViewModel组件
 * JetPack组件会在创建项目（AndroidX）的时候自动被包进去，但是，如果想使用ViewModel组件，还需要添加lifecycle依赖
 * 作者: 杨亮
 */
class CounterActivity : BaseActivity() {

    /**
     * 在kotlin中规定，所有定义在companion object语法结构中的方法都可以使用类似于Java中的static静态方法的形式调用
     */
    companion object {

        fun actionStart(context: Context) {
            val intent = Intent(context, CounterActivity::class.java)
            context.startActivity(intent)
        }

        private const val TAG = "CounterActivity"

        private const val COUNT_SP = "counter_reserved"
    }

    private lateinit var sp: SharedPreferences

    private lateinit var viewModel: CounterViewModel
//    private var number: Int = 0

    /**
     * 1、ViewModel一个重要的作用就是了以帮助Activity分担一部分工作，它是专门用于存放与界面相关的数据的；
     * 也就是说，只要是界面上能看得到的数据，它的相关变量都应该存放在ViewModel中
     *
     * 2、ViewModel的生命周期和Activity不同，它可以保证在手机屏幕发生旋转的时候不会被重新创建，只有当Activity退出的时候才会跟着Activity一起销毁；
     * 因此，将与界面相关的变量存放在ViewModel中，这样即使旋转手机屏幕，界面上显示的数据也不会丢失。
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        title = "JetPack组件集合-ViewModel组件"

        Log.e(TAG, "onCreate: ")

        //获取SharedPreferences实例
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(COUNT_SP, 0)

        //1、注意：绝对不能直接去创建ViewModel的实例，而是使用ViewModelProviders来获取CounterViewModel的实例
        //因为ViewModel有其独立的生命周期，并且生命周期要长于Activity
        //如果在onCreate()方法中创建ViewModel的实例，那么每次onCreate()方法执行的时候，ViewModel都会创建一个新的实例，这样当手机屏幕发生旋转的时候，就无法保留其中的数据了
//        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //传参ViewModel，这里of额外传入了CounterViewModelFactory参数，这里将读取到的countReserved传给了CounterViewModelFactory的构造函数
        //注意，这一步是非常重要的，只有用这种写法，才能将计数值最终传给CounterViewModel的构造函数。
        viewModel = ViewModelProviders.of(this, CounterViewModelFactory(countReserved))
            .get(CounterViewModel::class.java)

        refreshCounter()

        btnPlusOne.setOnClickListener {
//            number++
            viewModel.counter++
            refreshCounter()
        }

        btnClear.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
    }

    private fun refreshCounter() {
//        tvCounter.text = number.toString()
        tvCounter.text = viewModel.counter.toString()

    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    /**
     * 在onPause()中保存计数数据到本地，这样保证不管程序是退出还是进入后台，计数都不会丢失
     */
    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
        //保存计数数据
        sp.edit {
            putInt(COUNT_SP, viewModel.counter)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }


}