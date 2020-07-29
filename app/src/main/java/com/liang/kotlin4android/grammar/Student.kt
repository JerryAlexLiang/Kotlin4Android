package com.liang.kotlin4android.grammar

/**
 * 创建日期：2020/7/28 on 7:24 PM
 * 描述:学生类 - 继承与实现  在kotlin中 继承 ":"---> Java中的extends  实现":"---> Java中的implements
 * 作者:yangliang
 */
//class Student : Person() {
//
//    var number = ""
//    var grade = ""
//
//}

/**
 * 主构造函数与次构造函数  在kotlin中，public是默认修饰符
 */
class Student(private val number: String, private val grade: String) : Person() {

    //1、主构造函数的特点是没有函数体，直接定义在类名后面即可，当然也可以显式的给它指明参数
    //2、因为构造函数中的参数是在创建实例的时候传入的，不像之前那样需要重新赋值，所以可以将所有参数声明为val变量
    //3、主函数没有函数体，如果想在主函数中编写一些逻辑，可以使用init结构体
    init {
        println("init 姓名: $name  年龄: $age  学号是: $number  年级是: $grade")
    }

    fun getStudent(){
        println("fun 姓名: $name  年龄: $age  学号是: $number  年级是: $grade")
    }
    //4、 : Person()  子类的构造函数必须调用父类中的构造函数
    //在kotlin中，子类的构造函数调用父类的哪个构造函数，在继承的时候通过（）括号里指定
    //在这里，Person后面的一对空括号（）表示Student类的主构造函数在初始化的时候会调用Person类的无参构造函数，
    //即使在无餐的情况下，这对括号也不能省略

    //5、次构造函数关键字
//    constructor()

}