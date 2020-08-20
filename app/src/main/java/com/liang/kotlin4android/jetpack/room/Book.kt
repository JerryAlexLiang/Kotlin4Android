package com.liang.kotlin4android.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 创建日期: 2020/8/20 on 10:06 AM
 * 描述:
 * 1、关键字data，声明User是一个数据类型，另外，当一个类中没有任何代码时，可以将尾部的大括号{}省略掉
 * 2、Room数据库  良好的数据库编程建议是，给每一个实体类都添加一个id字段，并将这个字段设置为主键
 * 作者: 杨亮
 */
@Entity
//data class Book(var bookName: String, var authorName: String, var price: Double) {
data class Book(var bookName: String, var authorName: String, var price: Double, var pages: Int) {

    //1、在User的类名上使用@Entity注解，将它声明了一个实体类
    //2、添加一个id字段，并使用PrimaryKey注解将它设置为主键，再把autoGenerate参数指定为true，使得主键的值是自动生成的

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
