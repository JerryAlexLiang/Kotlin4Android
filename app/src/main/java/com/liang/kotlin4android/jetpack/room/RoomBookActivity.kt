package com.liang.kotlin4android.jetpack.room

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_room_book.*
import org.jetbrains.anko.toast
import kotlin.concurrent.thread

/**
 * 创建日期:2020/8/20 on 11:13 AM
 * 描述: Room ORM对象关系映射
 * 作者: 杨亮
 */
class RoomBookActivity : BaseActivity() {

    companion object {

        fun actionStart(context: Context) {
            val intent = Intent(context, RoomBookActivity::class.java)
            context.startActivity(intent)
        }

        private const val TAG = "RoomBookActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_book)

        title = "Room"

        val bookDao = AppDatabase.getDatabase(this).bookDao()

        val book1 = Book("微微一笑很倾城", "丁程鑫", 98.99, 356)
        val book2 = Book("第二人生", "马嘉祺", 78.66, 228)
        val book3 = Book("琉璃美人煞", "贺峻霖", 87.66, 879)
        val book4 = Book("陈情令", "刘耀文", 75.06, 796)
        val book5 = Book("余生请多指教", "李天泽", 68.66, 632)
        val book6 = Book("该死的温柔", "马天宇", 108.5, 159)

        btnAddBook.setOnClickListener {
            //由于数据库操作属于耗时操作，Room默认是不允许在主线程中进行数据库操作的，因此将增删改查的功能放在了子线程中进行
            thread {
                //将insertBook()返回的主键id值赋值给原来的Book对象，因为使用@Update和@Delete注解去更新和删除数据时都是基于这个id主键来操作的
                book1.id = bookDao.insertBook(book1)
                book2.id = bookDao.insertBook(book2)
                book3.id = bookDao.insertBook(book3)
                book4.id = bookDao.insertBook(book4)
                book5.id = bookDao.insertBook(book5)
                book6.id = bookDao.insertBook(book6)

                runOnUiThread {
                    Log.e(
                        TAG,
                        "填充数据库：" + "\n" + book1.bookName + "\n" + book2.bookName + "\n" + book3.bookName
                                + "\n" + book4.bookName + "\n" + book5.bookName + "\n" + book6.bookName + "\n"
                    )

                }
            }
        }

        btnUpdateBook.setOnClickListener {
            thread {
                book1.bookName = etScan.text.toString().trim()
                bookDao.updateBook(book1)
            }
        }

        btnDeleteBookByEntry.setOnClickListener {
            thread {
                bookDao.deleteBooks(book2)
            }
        }

        btnDeleteAllBook.setOnClickListener {
            thread {
                for (book in bookDao.loadAllBooks()) {
                    bookDao.deleteBooks(book)
                }
                runOnUiThread {
                    toast("数据库清除完毕！")
                }
            }
        }

        btnGetBook.setOnClickListener {

            tvBookInfo.text = ""

            thread {
                if (bookDao.loadAllBooks().isNotEmpty()) {
                    for (book in bookDao.loadAllBooks()) {
                        runOnUiThread {
                            Log.e(TAG, "数据库：$book")
                            tvBookInfo.append(book.toString() + "\n")
                        }
                    }
                } else {
                    runOnUiThread {
                        toast("数据库暂无数据！")
                        tvBookInfo.append("数据库暂无数据！")
                    }
                }

            }
        }

        btnQueryBookByPrice.setOnClickListener {

            tvBookInfo.text = ""

            thread {
                val trim = etScan.text.toString()
                if (trim.isEmpty()) {
                    runOnUiThread {
                        toast("请输入价钱")
                    }
                } else {
                    val loadBooksOrderThan = bookDao.loadBooksOrderThan(trim.toDouble())

                    if (loadBooksOrderThan.isNotEmpty()) {
                        for (book in loadBooksOrderThan) {
                            runOnUiThread {
//                        tvBookInfo.append("price大于" + trim + "元的书籍有: \n$book\n")
                                tvBookInfo.append("price大于 $trim 元的书籍有: \n$book\n")
                            }
                        }
                    } else {
                        runOnUiThread {
                            toast("数据库中暂未查询到价格大于 $trim 元的书籍！")
                            tvBookInfo.text = ("数据库中暂未查询到价格大于 $trim 元的书籍！")
                        }
                    }
                }
            }
        }

        btnDeleteBookByName.setOnClickListener {
            thread {
                val trim = etScan.text.toString().trim()
                bookDao.deleteBookByBookName(trim)
            }
        }

        tvClearContent.setOnClickListener {
            tvBookInfo.text = ""
        }
    }

    override fun isSetTransparencyBar(): Boolean {
        return false
    }
}