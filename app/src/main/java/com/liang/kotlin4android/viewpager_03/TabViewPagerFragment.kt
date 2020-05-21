package com.liang.kotlin4android.viewpager_03


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.fragment_tab_view_pager.*

/**
 * 创建日期：2020/5/21 on 14:20
 * 描述: 利用伴生对象定义获取碎片实例的静态方法
 * 作者: liangyang
 */
class TabLayoutPagerFragment : Fragment() {

    private var fragmentContent: String? = null
    private var fragmentFlag: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tab_view_pager, container, false)
        //碎片内部通过arguments获取外部的输入参数
        if (arguments != null) {
            fragmentFlag = arguments!!.getInt("fragmentFlag", 0)
            fragmentContent = arguments!!.getString("fragmentContent")

            Log.d("tag", "fragmentFlag: $fragmentFlag  fragmentContent:  $fragmentContent")
        }

//        val tvFragmentFlag = view.findViewById<TextView>(R.id.tvFragmentFlag)
//        val tvFragmentContent = view.findViewById<TextView>(R.id.tvFragmentContent)
//
//        tvFragmentFlag.text = """$fragmentFlag"""
//        tvFragmentContent.text = fragmentContent

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvFragmentFlag.text = """$fragmentFlag"""
        tvFragmentContent.text = fragmentContent
    }

    //利用伴生对象定义获取碎片实例的静态方法
    companion object {
        fun newInstance(fragmentFlag: Int, fragmentContent: String): TabLayoutPagerFragment {
            val fragment = TabLayoutPagerFragment()
            val bundle = Bundle()
            bundle.putInt("fragmentFlag", fragmentFlag)
            bundle.putString("fragmentContent", fragmentContent)
            //外部通过arguments向碎片传递输入参数
            fragment.arguments = bundle
            return fragment
        }
    }

}
