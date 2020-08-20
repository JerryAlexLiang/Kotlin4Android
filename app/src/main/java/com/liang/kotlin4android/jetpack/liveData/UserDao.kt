package com.liang.kotlin4android.jetpack.liveData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * 创建日期: 2020/8/20 on 4:12 PM
 * 描述:
 * 作者: 杨亮
 */
@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Query("select * from User")
    fun loadAllUser(): List<User>
}