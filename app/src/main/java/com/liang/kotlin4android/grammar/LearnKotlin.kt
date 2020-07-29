package com.liang.kotlin4android.grammar

import kotlin.math.max

/**
 * 创建日期：2020/7/20 on 10:51 PM
 * 描述:Kotlin语法学习
 * 作者:yangliang
 */
fun main() {
    val a = 77
    val b = 86

//    val value = largerNumber(a, b)
//    val value = largerNumber2(a, b)
//    print("larger number is: $value")

//    val score = getScore("丁程鑫")
//    print("分值: $score")

//    checkNumber(10F)

    //4、循环语句 while循环 for循环 for-i 舍弃  for-each -> for-in
    //[0,10]
//    for (i in 0..10) {
//        print("打印【0，10】")
//        println("\n" + i)
//    }
//
//    //[0,10)  until
//    for (i in 0 until 10) {
//        print("打印【[0,10)")
//        println("\n" + i)
//    }
//
//    //i = i + 2
//    for (i in 0 until 10 step 2) {
//        print("打印【[0,10) i = i + 2")
//        println("\n" + i)
//    }
//
//    //降序 downTo [10,1]
//    for (i in 10 downTo 1){
//        print("打印 降序 downTo [10,1]")
//        println("\n" + i)
//    }

    //5、类与对象
//    val person = Person()
//    person.name = "丁程鑫"
//    person.age = 18
//    person.getPerson()
    val student = Student("001", "高二")
    student.age = 18
    student.name = "丁程鑫"
    student.getStudent()

    //6、数据类
    val cellPhone1 = CellPhone("华为", 3999.0)
    val cellPhone2 = CellPhone("华为", 3999.0)
    println(cellPhone1)
    println(cellPhone2)
    println("cellPhone1 equals cellPhone2   " + (cellPhone1 == cellPhone2))
    println("""cellPhone1 equals cellPhone2   ${cellPhone1 == cellPhone2}""")

    //7、单例模式
    //Java写法获取单例类
    val singletonJava = SingletonJava.getInstance()
    singletonJava.SingletonJavaTest()
    //kotlin中调用单例类比较简单，比较类似于Java中静态方法的调用方式：
    //这种写法虽然看上去像是静态方法的调用，但其实kotlin在背后自动帮我们创建了一个SingletonKotlin类的实例
    //并且保证全局只会存在一个SingletonKotlin实例
    SingletonKotlin.singletonKotlinTest()

}

/**
 * 1、变量 -> 比较两个整型数的大小
 */
//fun largerNumber(number1: Int, number2: Int): Int {
//    return max(number1, number2)
////    return min(number1,number2)
//}

//语法糖：kotlin允许我们不必写函数体，可以直接将唯一的一行代码写在函数定义的尾部，中间用等号连接即可
//fun largerNumber(number1: Int, number2: Int): Int = max(number1, number2)
//语法糖：kotlin类型推导机制->省略函数体的返回类型
fun largerNumber(number1: Int, number2: Int) = max(number1, number2)

/**
 * 2、if条件语句
 * kotlin中的条件语句主要有两种实现方式：if和when
 */
//fun largerNumber2(number1: Int, number2: Int): Int {
////    var value = 0
////    if (number1 > number2) {
////        value = number1
////    } else {
////        value = number2
////    }
////    return value
//
//    //kotlin中的if语句相比Java有一个额外的功能，就是可以有返回值，即if语句每一个条件中的最后一行代码的返回值
////    val value = if (number1 > number2) {
////        number1
////    } else {
////        number2
////    }
////    return value
//
//    return if (number1 > number2) {
//        number1
//    } else {
//        number2
//    }
//}
//fun largerNumber2(number1: Int, number2: Int) = if (number1 > number2) {
//    number1
//} else {
//    number2
//}
fun largerNumber2(number1: Int, number2: Int) = if (number1 > number2) number1 else number2

/**
 * 3、when条件语句  kotlin中的when语句类似于Java中的switch语句，但是却比switch语句强大的多
 *
 * 语法：when结构体中的一系列的条件的结构体是：
 *
 *      1、精确匹配  匹配值->{执行逻辑}    当执行逻辑只有一行代码的时候，可以省略{}
 *      2、类型匹配  is （类似Java中的instance of）
 */
fun getScore(name: String) = when (name) {
    "丁程鑫" -> 100
    "马嘉祺" -> 98
    "刘耀文" -> 96
    "敖子逸" -> 98
    "宋亚轩" -> 96
    "姚景元" -> 97
    "贺峻霖" -> 95
    "李天泽" -> 99
    else -> 0
}

fun checkNumber(number: Number) {
    when (number) {
        is Int -> print("number is Int")
        is Long -> print("number is Long")
        else -> print("number is not support")
    }
}

//4、循环语句 while循环 for循环 for-i 舍弃  for-each -> for-in
