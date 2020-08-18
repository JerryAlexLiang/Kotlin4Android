package com.liang.kotlin4android.jetpack.viewModel

import androidx.lifecycle.ViewModel

/**
 * 创建日期: 2020/8/18 on 3:56 PM
 * 描述: 比较好的编程规范是给每一个Activity和Fragment都创建一个对应的ViewModel
 * 作者: 杨亮
 */
//class CounterViewModel : ViewModel() {
//
//    //counter变量用于计数
//    var counter = 0
//}

class CounterViewModel(countReserved: Int) : ViewModel() {

    //countReserved用于记录之前保存的计数值，并在初始化的时候赋值给counter变量

    //counter变量用于计数
    var counter = countReserved
}