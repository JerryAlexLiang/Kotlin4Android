package com.liang.kotlin4android.jetpack.room

import androidx.room.*

/**
 * 创建日期: 2020/8/20 on 10:08 AM
 * 描述: Room - Dao
 * 1、所有访问数据库的操作都封装在Dao里，覆盖所有的业务需求，使得业务方永远只需要与Dao层进行交互，而不必和底层的数据库打交道；
 * 2、Room是支持在编译时动态检查SQL语句语法的，也就是说如果我们编写的SQL语句有语法错误，编译时就会直接报错，减少了很多安全隐患;
 * 3、BookDao类上面使用了一个@Dao注解，这样Room才能将它识别为一个Dao.
 * 作者: 杨亮
 */
@Dao
interface BookDao {

    /**
     * 表示会将参数中传入的Book对象插入数据库中，插入完成后还会将自动生成的主键id值返回，
     * 直接使用注解即可，不用编写SQL语句
     */
    @Insert
    fun insertBook(book: Book): Long

    /**
     * 表示会将参数中传入的Book对象更新到数据库当中
     * 直接使用注解即可，不用编写SQL语句
     */
    @Update
    fun updateBook(newBook: Book)

    /**
     * 表示会将参数传入的Book对象从数据库中删除
     * 直接使用注解即可，不用编写SQL语句
     */
    @Delete
    fun deleteBooks(book: Book)

    //如果想从数据库中查询数据，或者使用非实体类参数来增删改数据，那么就必须编写SQL语句了

    /**
     * 查询所有数据，如果只添加注解，Room是无法知道我们想要查询哪些数据，因此必须在@Query注解中编写具体的SQL语句才行
     */
    @Query("select * from Book")
    fun loadAllBooks(): List<Book>

    /**
     * 还可以将方法中传入的参数指定到SQL语句中，如下：查询所有书价大于指定参数的Book
     */
    @Query("select * from Book where price> :price")
    fun loadBooksOrderThan(price: Double): List<Book>

    /**
     * 如果使用非实体类参数来 增删改 数据，也需要编写SQL语句才行；
     * 而且这个时候不能使用@Insert、@Update或@Delete注解，而是都要使用@Query注解才行
     */
    @Query("delete from Book where bookName= :bookName")
    fun deleteBookByBookName(bookName: String): Int
}