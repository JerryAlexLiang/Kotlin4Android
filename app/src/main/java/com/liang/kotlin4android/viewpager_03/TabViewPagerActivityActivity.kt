package com.liang.kotlin4android.viewpager_03

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_tab_view_pager_activity.*

/**
 * 创建日期：2020/5/21 on 13:53
 * 描述: TabLayout + ViewPager + Fragment
 * 作者: liangyang
 */
class TabViewPagerActivityActivity : BaseActivity() {

    private var titleList = ArrayList<String>()
    private var fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_view_pager_activity)

        title = "TabLayout + ViewPager + Fragment"

        //初始化数据源
        initData()

        //TabLayout联动ViewPager
        tabLayout.setupWithViewPager(viewPager)

        //viewPager.addOnPageChangeListener(this)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
    }

    private fun initData() {
        //tabLayout
        titleList.add("One")
        titleList.add("Two")
        titleList.add("Three")
        titleList.add("Four")
        titleList.add("Five")
        titleList.add("Six")
        titleList.add("Seven")

        fragmentList.add(TabLayoutPagerFragment.newInstance(1, "Page One"))
        fragmentList.add(TabLayoutPagerFragment.newInstance(2, "Page Two"))
        fragmentList.add(TabLayoutPagerFragment.newInstance(3, "Page Three"))
        fragmentList.add(TabLayoutPagerFragment.newInstance(4, "Page Four"))
        fragmentList.add(TabLayoutPagerFragment.newInstance(5, "Page Five"))
        fragmentList.add(TabLayoutPagerFragment.newInstance(6, "Page Six"))
        fragmentList.add(TabLayoutPagerFragment.newInstance(7, "Page Seven"))

        //初始化适配器
        val tabLayoutPagerAdapter =
            TabLayoutPagerAdapter(fragmentList, titleList, supportFragmentManager)
        //设置适配器
        viewPager.adapter = tabLayoutPagerAdapter
    }
}
