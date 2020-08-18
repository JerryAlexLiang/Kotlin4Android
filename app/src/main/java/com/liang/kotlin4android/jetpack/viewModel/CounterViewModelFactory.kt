package com.liang.kotlin4android.jetpack.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 创建日期: 2020/8/18 on 5:18 PM
 * 描述: 向ViewModel中传递参数
 * 新建CounterViewModelFactory类，实现ViewModelProvider.Factory接口
 *
 * 2、所有的ViewModel实例都是通过ViewModelProviders来获取的，因此没有任何地方可以向ViewModel的构造函数中传递参数
 *    需要借助ViewModelProvider.Factory来实现
 * 作者: 杨亮
 */
class CounterViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {

    /**
     * 在CounterViewModelFactory的构造函数中也和CounterViewModel一样接收一个countReserved参数；
     * 另外，ViewModelProvider.Factory接口要求实现create()方法
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //（1）、在create()方法中创建了ViewModel的实例，并将countReserved参数传了进去，实现向ViewModel中传递参数
        //（2）、为什么在ViewModelProvider.Factory接口实现的create()中就可以创建ViewModel实例呢？
        // 因为create()方法的执行时机和Activity的生命周期无关，所以不会产生之前提到的问题
        return CounterViewModel(countReserved) as T
    }
}