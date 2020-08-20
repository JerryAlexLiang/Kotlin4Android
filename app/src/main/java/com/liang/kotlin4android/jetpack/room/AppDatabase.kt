package com.liang.kotlin4android.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.liang.kotlin4android.jetpack.liveData.User
import com.liang.kotlin4android.jetpack.liveData.UserDao

/**
 * 创建日期: 2020/8/20 on 10:50 AM
 * 描述: 定义DataBase
 * 写法是非常固定的，只需要定义好3各部分的内容：
 * 1、数据库的版本号；
 * 2、包含哪些实体类；
 * 3、提供Dao层的访问实例
 * 作者: 杨亮
 */

//1、多个实体类之间可以用","隔开；
//2、AppDatabase必须继承RoomDatabase类，并且一定要使用abstract关键字将它声明为抽象类；
//3、然后提供相应的抽象方法，用于获取之前编写的Dao的实例：bookDao()，只需要进行方法声明就可以了，具体的方法实现是由Room在底层自动完成的；
//4、Room的数据库升级，非常繁琐，不能自动升级，每次升级都需要手动编写升级逻辑；
//eg： （1）、随着业务逻辑的升级，现在打算在数据库中添加一张User表，首选创建一个User的实体类
//      Room升级建表等实现完成后，当我们进行任何数据库操作时，Room就会自动根据当前数据库的版本号执行这些升级逻辑，从而让数据库保证是最新的版本
//      (2)、不过，每次数据库升级并不一定都要新增一张表，也有可能是向现有的表中添加新的列，这种情况只需要使用alter语句修改表结构就可以了


//@Database(version = 1, entities = [Book::class])
@Database(version = 2, entities = [Book::class, User::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    abstract fun userDao(): UserDao

    /**
     * 单例模式，全局应该只存在一份AppDatabase实例
     */
    companion object {

        //5、升级Room数据库
        //实现了一个Migration的匿名类，并传入了1和2这两个参数，表示当数据库版本从1升级到2的时候就执行这个匿名类中的升级逻辑
        //匿名类实例的变量命名比较讲究，这里命名成MIGRATION_1_2，可读性更高。
        private val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {
                //由于要新增一张User表，所以需要在migrate()方法中编写相应的建表语句
                //另外，需要注意的是，User表的建表语句必须和User实体类中声明的结构完全一致，否则Room就会抛出异常
                database.execSQL("create table User (id integer primary key autoincrement not null, firstName text not null,lastName text not null, age integer not null)")
            }

        }

        //6、Book表新增字段，即实体类的字段发生了变动，那么对应的数据库表也必须升级了
        private val MIGRATION_2_3 = object : Migration(2, 3) {

            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column pages integer not null default 0")
            }

        }

        //1、使用instance变量来缓存AppDatabase实例
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            //在getDatabase()中判断
            //2、如果instance变量不为空，就直接返回
            instance?.let {
                return it
            }

            //3、如果instance变量为空，就调用Room.databaseBuilder()方法来构建一个AppDatabase实例
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                //升级Room时，最后在侯建AppDatabase实例的时候，加入一个addMigrations()方法，并把MIGRATION_1_2传入即可
//                .addMigrations(MIGRATION_1_2)
                //升级数据库
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
                .apply {
                    //4、将创建出来的实例赋值给instance变量，然后返回当前实例即可
                    instance = this
                }
        }
    }

}
