package com.liang.kotlin4android.grammar

/**
 * 创建日期：2020/7/29 on 7:19 PM
 * 描述:kotlin - Lambda编程
 * 作者:yangliang
 */
fun main() {

    //1、不可变集合 - listOf()创建的是一个不可变的集合（只可用于读取，无法对集合进行添加、修改或者删除操作）
    val list = ArrayList<String>()
    list.add("丁程鑫")
    list.add("马嘉祺")
    list.add("刘耀文")
    list.add("姚景元")
    list.add("李天泽")
    list.add("敖子逸")
    list.add("宋亚轩")
    list.add("贺峻霖")

    val list2 = listOf("丁程鑫", "马嘉祺", "刘耀文", "姚景元", "李天泽", "敖子逸", "宋亚轩", "贺峻霖")
    //for-in循环语句：不仅可以用来遍历区间，也可以用来遍历集合
    for (star in list) {
        println("=====>1  ${list.size}")
        println("=====>1  $star")
    }

    println()

    for (star in list2) {
        println("=====>2  ${list2.size}")
        println("=====>2  $star")
    }

    println()

    //2、创建一个可变集合
    val list3 = mutableListOf("丁程鑫", "马嘉祺", "刘耀文", "姚景元", "李天泽", "敖子逸", "宋亚轩", "贺峻霖")
    for (star in list3) {
        println("=====>3  ${list3.size}")
        println("=====>3  $star")
    }

    println()

    list3.add("费启鸣")
    for (star in list3) {
        println("=====>4  ${list3.size}")
        println("=====>4  $star")
    }

    //3、Set集合

    println()

    //4、map集合
    val map = mapOf(
        "丁程鑫" to 1,
        "马嘉祺" to 2,
        "刘耀文" to 3,
        "姚景元" to 4,
        "李天泽" to 5,
        "敖子逸" to 6,
        "宋亚轩" to 7,
        "贺峻霖" to 8
    )
    //在for循环中将Map的键值对变量一起声明到了一对括号里面，
    // 这样当进行循环遍历时，每次遍历的结果就会赋值给这两个减值对变量
    for ((star, index) in map) {
//        println("star is ： " + star + "   index = " + index)
        println("star is ： $star   index = $index")
    }

    println()

    for (star in map) {
        println("star2 is $star")
    }

    println()

    //5、Lambda就是一小段可以作为参数传递的代码
    //查找集合中单词最长的水果
    val fruitList = listOf("apple", "banana", "orange", "pear", "watermelon")
    var maxLengthFruit = ""
    for (fruit in fruitList) {
        if (fruit.length > maxLengthFruit.length) {
            maxLengthFruit = fruit
        }
    }
    println("1 max length fruit is $maxLengthFruit")

    println()

    val maxLengthFruit2 = fruitList.maxBy { it.length }
    println("2 max length fruit is $maxLengthFruit2")

    println()

    //Lambda表达式的语法结构
    // {参数名1：参数类型，参数名2：参数类型 -> 函数体}
    val maxLengthFruit3 = fruitList.maxBy({ fruit: String -> fruit.length })
    //kotlin规定，如果Lambda参数是函数的最后一个参数的时候，可以将Lambda表达式移到函数括号的外面：
    val maxLengthFruit4 = fruitList.maxBy() { fruit: String -> fruit.length }
    //kotlin规定，如果Lambda参数是函数的唯一一个参数的话，还可以将函数的括号省略：
    val maxLengthFruit5 = fruitList.maxBy { fruit: String -> fruit.length }
    //由于kotlin的类型推导机制，Lambda表达式中的参数列表其实在大多数情况下不必声明参数类型
    val maxLengthFruit6 = fruitList.maxBy { fruit -> fruit.length }
    //最后，当Lambda表达式的参数列表中只有一个参数时，也不必声明参数名，而是可以使用it
    val maxLengthFruit7 = fruitList.maxBy { it -> it.length }
    val maxLengthFruit8 = fruitList.maxBy { it.length }
    println("3 max length fruit is $maxLengthFruit3")
    println("4 max length fruit is $maxLengthFruit4")
    println("5 max length fruit is $maxLengthFruit5")
    println("6 max length fruit is $maxLengthFruit6")
    println("7 max length fruit is $maxLengthFruit7")
    println("8 max length fruit is $maxLengthFruit8")

    println()

    //6、Java函数式API的调用
    //kotlin完全舍弃了new关键字，因此创建匿名类实例的时候就不能用new了，而是改用了object关键字
    Thread(object : Runnable {
        override fun run() {
            println("Thread is running 1")
        }
    }).start()

    //kotlin Java函数式API的调用：如果我们在kotlin代码中调用了一个Java方法，并且该方法接收一个Java单抽像方法接口参数，就可以使用函数式API
    //Java单抽象方法接口指的是接口中中有一个待实现的方法，如果接口中有多个待实现的方法，则无法使用函数式API
    Thread(Runnable { println("Thread is running 2") }).start()

    //将接口名进行省略
    Thread {
        println("Thread is running 3")
    }.start()

    println()

    //7、空指针检查
    //kotlin利用编译时判空检查的机制几乎杜绝了空指针异常
    //kotlin默认所有的参数和变量都不可以为空 - > kotlin将空指针异常的检查提前到了编译时期

    //7.1 可空类型系统 （在类名的后面加上一个问号?）
    //在使用可为空的类型系统时，需要在编译时期就将所有潜在的空指针异常都处理掉，否则代码将无法编译通过
    //例如： Int表示不可为空的整型，而Int?就表示可为空的整型； String表示为不可为空的字符串，而String?就表示可为空的字符串

    //Java中的非空判断 !=null 在kotlin中使用?.操作符可以简化
//    if (a!=null){
//        a.doSomething()
//    }

//    a?.doSomething()

    //?:操作符 这个操作符的左右两边都接收一个表达式，如果左边表达式的结果不为空就返回左边表达式的结果，否则就返回右边表达式的结果

    //demo：编写一个函数用来获取一段文本的长度
    //传统写法：
    //由于文本可能为空，所以需要先进行一次非空判断
    fun getTextLength(text: String?): Int {
        if (text != null) {
            return text.length
        }
        return 0
    }

    //首先，由于text时可能为空的，所以在调用它的length字段时需要使用?.操作符，当text为空时，text?.length会返回一个null值，这个时候再借助?:操作符让它返回0
    fun getTextLength2(text: String?) = text?.length ?: 0

    //非空断言工具 写法：在对象的后面加上!!

    //辅助工具let let既不是操作符，也不是关键字，而是一个函数，
    // 这个函数提供了函数式API的编程接口，并将原始调用对象作为参数传递到Lambda表达式中
    //示例代码
//    obj.let {
//        obj2 - >
//        //编写具体的业务逻辑
//    }

    //解释说明：这里调用了obj对象的let函数，然后Lambda表达式中的代码就会立即执行，
    // 并且这个obj对象本身还会做为参数传递到Lambda表达式中，这里obj和obj2实际上是同一个对象，这就是let函数的作用
    //作用：let函数和?.操作符可以在空指针检查的时候起到很大的作用


    println()

    //8、kotlin中字符串内嵌表达式的语法规则：
    //"hello, ${obj.name}. nice to meet you!"
    //kotlin允许我们在字符串中嵌入${}这种语法结构的表达式，并在运行时使用表达式执行的结果替代者一部分内容
    //另外，当表达式中仅有一个变量的时候没还可以将两边的大括号省略
    val student = Student("001", "大一")
    student.name = "Jerry"
    student.age = 21


    println("hello,${student.name}. nice to meet you!")

    println()

    val name = "丁程鑫"
    val age = 19
    println("Student(name=$name,age=$age)")

    println()

    //9、函数的参数默认值
//    printParam("world")
    //kotlin的神器机制，通过键值对的方式来传参，从而不必像传统写法那样按照参数定义的顺序来传参
    //此时，哪个参数在前哪个参数在后都无所谓，kotlin可以准确的将参数匹配上
    printParam(str = "Jerry", num = 150)
    printParam(
        str = "Jerry",
        num = 100
    )
    //使用键值对传参的方式之后，可以省略默认的参数值num参数
    printParam(str = "Alex")

}

//9、函数的参数默认值
fun printParam(num: Int = 100, str: String) {
    println("num is$num, str is $str")
}
