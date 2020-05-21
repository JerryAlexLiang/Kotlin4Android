package com.liang.kotlin4android.widget_02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.lv_fruit_item.view.*

/**
 * 创建日期：2020/5/20 on 12:56
 * 描述: ListView适配器
 * 作者: liangyang
 */
class FruitLvAdapter(context: Context, fruitList: List<Fruit>) : BaseAdapter() {

    //1、Kotlin中类的构造函数可以直接写在类名的后面
    //2、Kotlin中继承extends的使用  :
    //3、初始化可以放在init中进行

    var dataList: List<Fruit>? = null
    private var context: Context

    init {
        dataList = fruitList
        this.context = context
    }

    /**
     * 6、使用Kotlin时，不能直接使用convertView，因为在Kotlin中val对象是只可读的
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: MyViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lv_fruit_item, parent, false)
            viewHolder = MyViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MyViewHolder
        }

        //获取当前项的Fruit实例
//        val fruitData: Fruit = getItem(position) as Fruit
        val fruitData = getItem(position) as Fruit
        viewHolder.ivFruit?.setImageResource(fruitData.imageId)
        viewHolder.tvFruitName?.text = fruitData.name

        return view
    }

    override fun getCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //5、方法名像JavaScript一样使用  fun 方法名() : 返回类型{}
    override fun getItem(position: Int): Any {
        return this.dataList!![position]
    }

    /**
     * 4、内部函数前可加inner关键字，标明内部函数
     */
    inner class MyViewHolder(view: View) {

        var ivFruit: ImageView? = null
        var tvFruitName: TextView? = null

        init {
            ivFruit = view.ivFruit
            tvFruitName = view.tvFruitName
        }
    }
}
