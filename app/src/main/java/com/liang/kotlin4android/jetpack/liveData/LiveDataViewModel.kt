package com.liang.kotlin4android.jetpack.liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 创建日期: 2020/8/19 on 1:55 PM
 * 描述: LiveData与ViewModel结合使用
 * 作者: 杨亮
 */
class LiveDataViewModel(countReserved: Int) : ViewModel() {

    /**
     * 1、将counter变量定义为MutableLiveData对象，并指定泛型为Int，表示它包含的是整型数据
     * MutableLiveData是一种可变的LiveData，主要有3种读写数据的方法getValue()、setValue()、postValue()
     *
     * 语法糖：
     * getValue()方法用于获取LiveData中包含的数据；
     * setValue()方法用于给LiveData设置数据，只能在主线程中调用；
     * postValue()方法用于在非主线程中给LiveData设置数据
     */
//    val counter = MutableLiveData<Int>()

    /**
     * 在init结构体中给counter设置数据，这样之前保存的计数值就可以在初始化的时候得到恢复
     */
//    init {
//        counter.value = countReserved
//    }

//    fun plusOne() {
//        //先获取counter中包含的数据赋值改count
//        val count = counter.value ?: 0
//        //然后给他加1，再重新设置到counter中
//        //注意，调用LiveData的getValue()方法所获得的的数据可能是空的，因此使用?:操作符，当获取的数据为空的时候，就用0来作为默认计数
//        counter.value = count + 1
//    }
//
//    fun clear() {
//        counter.value = 0
//    }


    /**************************************************分割线*******************************************************************/

    //优化：
    //虽然，上面的写法可以正常工作,但是不是最规范的LiveData的用法，主要问题就是将counter这个可变的LiveData对象暴露给了外部，
    // 这样即使是在ViewModel的外面也是可以给counter设置数据的，从而破坏了ViewModel数据的封装性，同时也可能带来一定的风险

    //优化方法：
    //永远只暴露不可变的LiveData给外部。这样在非ViewModel中就只能观察LiveData的数据变化，而不能给LiveData设置数据

    //1、先将原来的变量名counter改为_counter变量，并加上private修饰符，这样_counter变量对于外部就是不可见的了
    private val _counter = MutableLiveData<Int>()

    //2、新定义一个counter变量，并将它的类型声明为不可变的LiveData，并在它的get()属性方法中返回_counter变量
    //这样，当外部调用counter变量的时候，实际上获得的就是_counter的实例，但是却无法给counter设置数据，从而保证了ViewModel的数据封装性
    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        //先获取_counter中包含的数据赋值改count
        val count = _counter.value ?: 0
        //然后给他加1，再重新设置到_counter中
        //注意，调用LiveData的getValue()方法所获得的的数据可能是空的，因此使用?:操作符，当获取的数据为空的时候，就用0来作为默认计数
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}