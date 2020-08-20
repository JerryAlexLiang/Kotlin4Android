package com.liang.kotlin4android.jetpack.liveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * 创建日期: 2020/8/19 on 1:55 PM
 * 描述: LiveData与ViewModel结合使用
 * 作者: 杨亮
 */
class LiveDataViewModel(countReserved: Int, user: User) : ViewModel() {

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

    /**************************************************分割线*******************************************************************/

    /**
     * map和switchMap
     * LiveData为了能够应对各种不同的需求场景，提供了两种转换方法：map()和switchMap()方法
     *
     * 1、map()方法：作用是将实际包含数据的LiveData和仅用于观察数据的LiveData进行转换
     * map()方法接收两个参数：
     * 第一个参数是原始的LiveData对象；
     * 第二个参数是一个转换函数，在转换函数里编写具体的转换逻辑
     *
     * 2、switchMap()方法：
     * 前面所有的内容都有一个前提：LiveData对象的实例都是在ViewModel中创建的，在实际项目中，很有可能ViewModel中的某个LiveData对象是调用另外的方法获取的
     * eg: 新建Repository单例类
     * 使用场景（非常固定，但是在实际项目中更常用）：
     * 如果ViewModel中的LiveData对象是调用另外的方法获取的，不是在ViewModel中创建的，那么就可以借助switchMap()方法，将这个LiveData对象转换成另一个可观察的LiveData对象
     *
     * switchMap()方法接收两个参数：
     * 第一个参数是传入我们新增的userIdLiveData，switchMap()方法会对它进行观察；
     * 第二个参数是一个转换函数，注意必须在这个转换函数中返回一个LiveData对象，因为switchMap()方法的工作原理就是将转换函数中返回的LiveData对象转换成里一个可观察的LiveData对象
     */

    //将userLiveData声明为private，以保证数据的封装性，外部使用的时候只要观察userName这个LiveData就可以了
    private val userLiveData = MutableLiveData<User>()

    //如果在Activity中明确只会显示用户的姓名，而不关心用户的年龄，那么这个时候还将整个User类型的LiveData暴露给外部，就显得不那么合适了
    //而map()就是专门用于解决这种问题的，它可以将User类型的LiveData自由地转型成任意其他类型的LiveData

    //定义一个userName变量，声明为不可变的LiveData
    //调用Transformations的map()方法对LiveData的数据类型进行转换
    //当userLiveData的数据发生变化的时候，map()方法会监听到变化并执行转换函数中的逻辑，然后再将转换之后的数据通知给userName的观察者
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        //转换逻辑：将User对象转换成一个只包含用户姓名的字符串
        "${user.firstName}  ${user.lastName}"
    }

    fun mapTrans() {
        val user2 = User("刘耀文", "贺峻霖", 14)
        userLiveData.value = user2
    }


    /**
     * switchMap()工作流程梳理
     * 1、当外部调用ViewModel中的getUser()方法来获取用户数据时，并不会发起任何请求或者函数调用，只会将传入的userId值设置到userIdLiveData当中；
     * 2、一旦userIdLiveData数据发生变化，那么观察userIdLiveData的switchMap()方法就会执行，并且调用我们编写的转换函数
     * 3、然后再转换函数中调用外部方法Repository.getUser(useId)获取正正的用户数据；
     * 4、同时switchMap()方法会将Repository.getUser(useId)方法返回的LiveData对象转换成一个可观察的LiveData对象，对于Activity而言，只要去观察这个LiveData对象就可以了
     */
    private val userIdLiveData = MutableLiveData<String>()
    private val userLiveData2 = MutableLiveData<User>()

    val mUser: LiveData<User> = Transformations.switchMap(userIdLiveData) { useId ->
        Repository.getUser(useId)
    }

    val userName2: LiveData<String> = Transformations.map(userLiveData2) { user2 ->
        "${user2.firstName}  ${user2.lastName}"
    }

    //在ViewModel中创建一个getUser()方法，去调用Repository单例类的getUser()方法来获取LiveData对象
    //注意：每次调用getUser()返回的都是一个新的LiveData
    //使用switchMap()方法观察新的LiveData
//    fun getUser(userId: String): LiveData<User> {
//        return Repository.getUser(userId)
//    }
    fun getUser(userId: String) {
        userIdLiveData.value = userId
        userLiveData2.value = User(userId, userId, 0)
    }

    init {
        _counter.value = countReserved
        userLiveData.value = user
        userLiveData2.value = mUser.value ?: User("丁程鑫", "马嘉祺", 18)
    }

}