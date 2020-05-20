package com.liang.kotlin4android.widget_02

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_list_view.*

/**
 * 创建日期：2020/5/20 on 12:46
 * 描述: ListView
 * 作者: liangyang
 */
class ListViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        title = "ListView"

        //初始化数据
        initData()
        val adapter = FruitLvAdapter(this, fruitList)
        listView.adapter = adapter

//        listView.setOnItemClickListener { parent, view, position, id ->
        listView.setOnItemClickListener { _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this, "点击了: ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initData() {
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
}
