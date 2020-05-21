@file:Suppress("DEPRECATION")

package com.liang.kotlin4android.viewpager_03

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * 创建日期：2020/5/21 on 14:36
 * 描述: TabLayout + ViewPager + Fragment 适配器
 * 作者: liangyang
 */
class TabLayoutPagerAdapter(
    var fragmentList: ArrayList<Fragment>?, var titleList: ArrayList<String>?,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager) {

    // 获取每个页面的碎片对象
    override fun getItem(position: Int): Fragment {
        return fragmentList?.get(position)!!
    }

    //获取页面的数量
    override fun getCount(): Int {
        return fragmentList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList?.get(position)
    }


}
