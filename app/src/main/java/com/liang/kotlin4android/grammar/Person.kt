package com.liang.kotlin4android.grammar

/**
 * 创建日期：2020/7/28 on 7:16 PM
 * 描述:继承与实现 在kotlin中，任何一个非抽象类默认都是不可以被继承的，相当于Java中给类声明了final关键字
 * 在Person类前面加open关键字--->抽象类--->使之可以被继承
 * 作者:yangliang
 */
open class Person {

    //因为需要创建对象之后，再指定具体的姓名和年龄，如果使用val声明的话,初始化之后就不能再次赋值了
    //所以，这里使用var关键字创建了name和age这两个字段
    var name = ""
    var age = 0

    fun getPerson() {
        println("学生姓名: $name  学生年龄: $age")
    }
}