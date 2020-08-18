package com.liang.kotlin4android.grammar

import android.content.Context
import android.widget.Toast

/**
 * 创建日期: 2020/8/10 on 7:31 PM
 * 描述: Kotlin中的静态方法
 * 作者: 杨亮
 */
class KotlinStaticUtil {

    //注意：
    //Kotlin中没有直接定义静态方法的关键字，但是提供了一些语法特性来支持类似与静态方法调用的写法

    //1、使用object单例类的写法会将整个类中的所有方法全部变成类似于静态方法的调用方式

    //2、而如果只是希望让类中的某一个方法变成静态方法的调用方式，就可以使用companion object 伴生类

    fun doAction1() {
        println("do action1 " + "\n")
    }

    /**
     * 使用单例类object或者伴生类companion object都只是在语法的形式上模仿了静态方法的调用方式，实际上它们并不是真正的静态方法
     * 因此如果在Java代码中一静态方法的形式去调用的话，会发现这些方法并不存在
     * 解决办法：
     * 1、在单例类object或者伴生类companion object中的方法上加上@JvmStatic注解，那么kotlin编译器就会将这些方法编译成真正的静态方法
     *
     * 注意：@JvmStatic注解只能加在单例类object或者伴生类companion object中的方法上，如果加在一个普通方法上，会直接提示语法错误
     *
     * 2、顶层方法：kotlin编译器会将所有的顶层方法全部编译成静态方法
     *    在kotlin代码中，所有的顶层方法都可以在任何位置被直接调用，不用管包名路径，也不用创建实例，直接键入fun方法即可
     */
    companion object {

        @JvmStatic
        fun doAction2(context: Context) {
            println("do action2 " + "\n")
            Toast.makeText(context, "do action2", Toast.LENGTH_SHORT).show()
        }

        fun doAction3(context: Context){
            println("do action3 " + "\n")
            Toast.makeText(context, "do action3", Toast.LENGTH_SHORT).show()
        }
    }


}