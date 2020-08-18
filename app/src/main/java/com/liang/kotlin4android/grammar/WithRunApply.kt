package com.liang.kotlin4android.grammar


/**
 * 创建日期: 2020/8/10 on 6:50 PM
 * 描述: 标准函数with、run和apply
 * 作者: 杨亮
 */
fun main() {

    val list = listOf("丁程鑫", "马嘉祺", "贺峻霖", "刘耀文", "敖子逸", "李天泽")

    appendStarts(list)

    println("结束========================================================" + "\n")

    //1、with函数
    //接收两个参数：第一个参数可以是一个任意类型的参数，第二个参数是一个Lambda表达式
    //with函数会在Lambda表达式中提供第一个参数对象的上下文，并使用Lambda表达式中的最后一行代码最为返回值
    //with函数的作用：可以在连续调用同一个对象的多个方法时让代码变得更加精简
    //在appendStarts()中，可以看到多次调用了builder对象的方法，这个时候可以考虑使用with函数来让代码变得更加精简
    appendStartsWith(list);

    println("结束========================================================" + "\n")

    //2、run函数
    //run函数是不能直接调用的，而是一定要调用某个对象的run函数才行；
    //run函数只接收一个Lambda参数，并且会在Lambda表达式中提供调用对象的上下文，其他方面和with函数一样
    //也是使用Lambda表达式中的最后一行代码作为返回值返回
    appendStartsRun(list)

    println("结束========================================================" + "\n")

    //3、apply函数
    //apply函数和run函数一样，都是在某个对象上调用，并且只接收一个Lambda参数，也会在Lambda表达式中提供调用对象的上下文
    //但是apply函数无法指定返回值，而是会自动返回调用对象本身
    appendStartsApply(list)

    println("结束========================================================" + "\n")

//    KotlinStaticUtil.doAction2()

}

fun appendStartsApply(list: List<String>) {
    println("Apply开始========================================================" + "\n")
    val result = StringBuilder().apply {
        append("Start eating starts. \n")
        for (start in list) {
            append(start).append("\n")
        }
        append("Ate all starts.")
    }
    //apply函数无法指定返回值，而是会自动返回调用对象本身
    //因此，这里的result实际上是一个StringBuilder对象，所以在最后打印的时候还要再调用它的toString()方法才行
    println(result.toString() + "\n")
}

fun appendStartsRun(list: List<String>) {
    println("Run开始========================================================" + "\n")
    //和with函数相比变化不大，只是将调用with函数并传入StringBuilder对象改成了调用StringBuilder对象的run方法，其他都没有任何区别
    val result = StringBuilder().run {
        append("Start eating starts. \n")
        for (start in list) {
            append(start).append("\n")
        }
        append("Ate all starts.")
        toString()
    }
    println(result + "\n")
}

fun appendStartsWith(list: List<String>) {
    println("With开始========================================================" + "\n")
    val result = with(StringBuilder()) {
        append("Start eating starts. \n")
        for (start in list) {
            append(start).append("\n")
        }
        append("Ate all starts.")
        toString()
    }
    println(result + "\n")
}

fun appendStarts(list: List<String>) {
    println("开始========================================================" + "\n")
    //使用StringBuilder来构建Start字符串
    val builder = StringBuilder()
    builder.append("Start eating starts. \n")
    for (start in list) {
        builder.append(start).append("\n")
    }
    builder.append("Ate all starts.")
    val result = builder.toString()
    println(result + "\n")
}

