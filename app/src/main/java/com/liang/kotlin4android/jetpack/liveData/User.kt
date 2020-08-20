package com.liang.kotlin4android.jetpack.liveData

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 创建日期: 2020/8/19 on 3:50 PM
 * 描述: 关键字data，声明User是一个数据类型，另外，当一个类中没有任何代码时，可以将尾部的大括号{}省略掉
 * 作者: 杨亮
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}