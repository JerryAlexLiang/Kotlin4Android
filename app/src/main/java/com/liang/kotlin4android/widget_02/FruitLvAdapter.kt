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

    var dataList: List<Fruit>? = null
    private var context: Context

    init {
        dataList = fruitList
        this.context = context
    }

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

    override fun getItem(position: Int): Any {
        return this.dataList!![position]
    }

    inner class MyViewHolder(view: View) {

        var ivFruit: ImageView? = null
        var tvFruitName: TextView? = null

        init {
            ivFruit = view.ivFruit
            tvFruitName = view.tvFruitName
        }
    }
}
