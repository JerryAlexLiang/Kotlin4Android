package com.liang.kotlin4android.grammar

/**
 * 创建日期：2020/7/29 on 7:01 PM
 * 描述:kotlin中创建单例类
 * 作者:yangliang
 */
object SingletonKotlin {

    //1、在kotlin中创建单例类非常简单，只需要将class关键字修改为object关键字即可

    //2、这就是个单例类了，可以在其中编写需要的函数
    fun singletonKotlinTest() {
        println("SingletonKotlin is created...")
    }

    //3、可以看出，在kotlin中，单例类，不需要私有化构造函数，也不需要提供getInstance这样的静态方法，
    //只需要把class关键字替换成object关键字，一个单例类就创建完成了
    //kotlin中调用单例类比较简单，比较类似于Java中静态方法的调用方式：
    //SingletonKotlin.singletonKotlinTest()
}