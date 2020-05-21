package com.liang.kotlin4android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.liang.kotlin4android.widget_02.Fruit
import kotlinx.android.synthetic.main.lv_fruit_item.view.*

/**
 * 创建日期：2020/5/20 on 13:41
 * 描述: RecyclerView的适配器
 * 作者: liangyang
 */
class MainRvAdapter(private val fruitList: List<Fruit>, private val resourceId: Int) :
    RecyclerView.Adapter<MainRvAdapter.MyViewHolder>() {

    private var itemClickListener: IKotlinItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.rv_fruit_item, parent, false)
            LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
//        val viewHolder = MyViewHolder(view)
        //        viewHolder.itemView.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val fruit = fruitList[position]
//            Toast.makeText(parent.context, "你点击了: ${fruit.name}", Toast.LENGTH_SHORT).show()
//        }
//        viewHolder.ivFruit?.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val fruit = fruitList[position]
//            Toast.makeText(parent.context, "你点击了: ${fruit.name}", Toast.LENGTH_SHORT).show()
//        }
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.tvFruitName!!.text = fruit.name
        holder.ivFruit!!.setImageResource(fruit.imageId)

        //Item点击事件
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(holder.itemView, position)
        }

        holder.itemView.setOnLongClickListener {
            itemClickListener!!.onItemLongClickListener(position)
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivFruit: ImageView? = null
        var tvFruitName: TextView? = null

        init {
            ivFruit = view.ivFruit
            tvFruitName = view.tvFruitName
        }
    }

    // 提供set方法
    fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    //自定义接口
    interface IKotlinItemClickListener {
        fun onItemClickListener(view: View, position: Int)

        fun onItemLongClickListener(position: Int): Boolean

    }

}

