package com.liang.kotlin4android.widget_02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * 创建日期：2020/5/20 on 13:32
 * 描述:  RecyclerView
 * 作者: liangyang
 */
class RecyclerViewActivity : BaseActivity() {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, RecyclerViewActivity::class.java)
            context.startActivity(intent)
        }
    }

    //1、对全局变量延迟初始化，使用的是lateinit关键字，它可以告诉kotlin编译器，会在晚些时候对这个变量进行初始化，这样就不用在一开始的时候将它赋值为null了
    //2、注意：使用lateinit关键字的风险：要确保它在被任何地方调用之前已经完成了初始化工作，否则kotlin将无法保证程序的安全性
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager

    //    private  var linearLayoutManager: LinearLayoutManager? = null
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        title = "RecyclerView"

        //初始化数据
        initData()

//        linearLayoutManager = LinearLayoutManager(this)
        //3、还可以通过代码来判断一个全局变量是否已经完成了初始化，这样在某些时候能够有效避免重复对某一个变量进行初始化操作
        //语法：  ::linearLayoutManager.isInitialized 可用于判断linearLayoutManager变量是否已经初始化
        if (!::linearLayoutManager.isInitialized) {
            linearLayoutManager = LinearLayoutManager(this)
        }
        gridLayoutManager = GridLayoutManager(this, 3)
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.layoutManager = linearLayoutManager
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val adapter = FruitRvAdapter(fruitList, R.layout.rv_fruit_item)
        recyclerView.adapter = adapter
    }

    override fun isSetTransparencyBar(): Boolean {
        return false
    }

    private fun initData() {
        fruitList.clear()
        repeat(3) {
            fruitList.add(Fruit("苹果", R.drawable.apple_pic))
            fruitList.add(Fruit("香蕉", R.drawable.banana_pic))
            fruitList.add(Fruit("橘子", R.drawable.orange_pic))
            fruitList.add(Fruit("西瓜", R.drawable.watermelon_pic))
            fruitList.add(Fruit("香梨", R.drawable.pear_pic))
            fruitList.add(Fruit("葡萄", R.drawable.grape_pic))
            fruitList.add(Fruit("菠萝", R.drawable.pineapple_pic))
            fruitList.add(Fruit("草莓", R.drawable.strawberry_pic))
            fruitList.add(Fruit("樱桃", R.drawable.cherry_pic))
            fruitList.add(Fruit("芒果", R.drawable.mango_pic))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_rv, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.LinearLayoutManager -> {
                initData()
//                recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
                recyclerView.layoutManager = linearLayoutManager
                val adapter = FruitRvAdapter(fruitList, R.layout.rv_fruit_item)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }

            R.id.GridLayoutManager -> {
                initData()
                recyclerView.layoutManager = gridLayoutManager
                val adapter = FruitRvAdapter(fruitList, R.layout.rv_fruit_item2)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }

            R.id.StaggeredGridLayoutManager -> {
                initFruits()
                recyclerView.layoutManager = staggeredGridLayoutManager
                val adapter = FruitRvAdapter(fruitList, R.layout.rv_fruit_item2)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
        }

        return true

    }

    private fun initFruits() {
        fruitList.clear()
        repeat(3) {
            fruitList.add(Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic))
            fruitList.add(Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic))
        }
    }

    private fun getRandomLengthName(name: String): String {
        val length = Random().nextInt(20) + 1
        val builder = StringBuilder()
        for (i in 0 until length) {
            builder.append(name)
        }
        return builder.toString()
    }
}
