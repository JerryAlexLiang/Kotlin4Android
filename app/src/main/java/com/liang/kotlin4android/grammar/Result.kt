package com.liang.kotlin4android.grammar

import java.lang.Exception
import java.lang.IllegalArgumentException

/**
 * 创建日期: 2020/8/18 on 2:46 PM
 * 描述: 密封类
 * 作者: 杨亮
 */
//interface Result
//
//class Success(val msg: String) : Result
//
//class Failure(val error: Exception) : Result

//fun getResultMsg(result: Result) = when (result) {
//
//    is Success -> result.msg
//
//    is Failure -> result.error.message
//
//    //必须在编写一个else条件，否则kotlin编译器会认为这里缺少条件分支，代码将无法编译通过，
//    // 另外else条件还有一个潜在的风险，如果新增一个类并实现Result接口，但是没有在getResultMsg()方法中添加相应的条件分支，
//    // 编译器在这种情况下是不会提醒我们的，而是会在运行的时候进入else条件里面，从而抛出异常并导致程序崩溃
//    else -> throw IllegalArgumentException()
//}


//针对上述问题，kotlin的密封类可以很好地解决这个问题；密封类的关键字是sealed class

//可以看到，代码没有太大的变化，只是将interface关键字改成了sealed class；另外，由于密封类是一个可继承的类，因此在继承它的时候需要在后面加上一个括号
sealed class Result

class Success(val msg: String) : Result()

class Failure(val error: Exception) : Result()

fun getResultMsg(result: Result) = when (result) {

    is Success -> result.msg

    is Failure -> result.error.message

    //这里else语句不需要了，而且仍能编译通过，是因为当在when语句中传入一个密封类变零作为条件时，kotlin编译器会自动检查该密封类有哪些子类，
    //并强制要求将每一个子类所对应的条件全部处理，这样就可以保证，即使没有编写else条件，也不可能会出现漏写条件分支的情况

    //注意：密封类及其所有子类只能定义在同一个文件的顶层位置，不能嵌套在其他类中，这是被密封类底层的实现机制所限制的。
}


