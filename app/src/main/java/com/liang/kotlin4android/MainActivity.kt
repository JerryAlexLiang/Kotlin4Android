package com.liang.kotlin4android

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.liang.kotlin4android.activity_01.StudyActivity
import com.liang.kotlin4android.broadcast.BroadcastActivity
import com.liang.kotlin4android.jetpack.lifecycles.LifecycleActivity
import com.liang.kotlin4android.jetpack.viewModel.CounterActivity
import com.liang.kotlin4android.threadNet.ThreadActivity
import com.liang.kotlin4android.viewpager_03.TabViewPagerActivityActivity
import com.liang.kotlin4android.widget_02.Fruit
import com.liang.kotlin4android.widget_02.ListViewActivity
import com.liang.kotlin4android.widget_02.RecyclerViewActivity
import kotlinx.android.synthetic.main.activity_recycler_view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 创建日期：2020/5/21 on 11:01
 * 描述: 导航页
 * 作者: liangyang
 */
class MainActivity : BaseActivity(), MainRvAdapter.IKotlinItemClickListener {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kotlin学习 - 导航页"

        //初始化数据
        initData()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        //添加Android自带的分割线
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val adapter = MainRvAdapter(fruitList, R.layout.rv_fruit_item)
        recyclerView.adapter = adapter

        //itemClick
        adapter.setOnKotlinItemClickListener(this)
    }


//    override fun onItemClickListener(position: Int) {
//        when (position) {
//            0 -> {
//                StudyActivity.actionStart(this)
//            }
//
//            1 -> ListViewActivity.actionStart(this)
//
//            2 -> RecyclerViewActivity.actionStart(this)
//
//            else -> {
////                Toast.makeText(this, "点击了: ${fruitList[position].name}", Toast.LENGTH_SHORT)
////                    .show()
////                toast("点击了: ${fruitList[position].name}")
//                //view.snackbar("login success!")
//                //view.longSnackbar("Wow, nice work!")
//                //view.snackbar("Action, reaction", "Click me!") { doSomeThing() }
//
//            }
//        }
//    }

    override fun onItemClickListener(view: View, position: Int) {
        when (position) {
            0 -> {
                StudyActivity.actionStart(this)
            }

            1 -> ListViewActivity.actionStart(this)

            2 -> RecyclerViewActivity.actionStart(this)

            3 -> startActivity<TabViewPagerActivityActivity>() //auto

            4 -> startActivity<BroadcastActivity>()

            5 -> startActivity<ThreadActivity>()

            7 -> CounterActivity.actionStart(this)

            8 -> LifecycleActivity.actionStart(this)

            else -> {
//                Toast.makeText(this, "点击了: ${fruitList[position].name}", Toast.LENGTH_SHORT)
//                    .show()
                toast("点击了: ${fruitList[position].name}")
            }
        }
    }

    override fun onItemLongClickListener(position: Int): Boolean {
        toast("点击了: $position + ${fruitList[position].name}")
        return true
    }

    private fun initData() {
        fruitList.add(Fruit("基础控件Widget和Activity", R.drawable.apple_pic))
        fruitList.add(Fruit("ListView(BaseAdapter)", R.drawable.banana_pic))
        fruitList.add(Fruit("RecyclerView", R.drawable.orange_pic))
        fruitList.add(Fruit("TabLayout + ViewPager + Fragment", R.drawable.watermelon_pic))
        fruitList.add(Fruit("BroadcastReceiver", R.drawable.pear_pic))
        fruitList.add(Fruit("线程Thread", R.drawable.grape_pic))
        fruitList.add(Fruit("网络请求框架Retrofit", R.drawable.pineapple_pic))
        fruitList.add(Fruit("JetPack组件集合-ViewModel组件", R.drawable.strawberry_pic))
        fruitList.add(Fruit("JetPack组件集合-LifeCycles组件", R.drawable.cherry_pic))
        fruitList.add(Fruit("芒果", R.drawable.mango_pic))

    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

}
