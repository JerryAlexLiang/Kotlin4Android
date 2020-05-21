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

    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        title = "RecyclerView"

        //初始化数据
        initData()

        linearLayoutManager = LinearLayoutManager(this)
        gridLayoutManager = GridLayoutManager(this, 3)
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.layoutManager = linearLayoutManager
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val adapter = FruitRvAdapter(fruitList, R.layout.rv_fruit_item)
        recyclerView.adapter = adapter
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
