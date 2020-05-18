package com.liang.kotlin4android.activity_01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_study.*

/**
 * 创建日期：2020/5/18 on 13:47
 * 描述: Activity的学习
 * 作者: liangyang
 */
class StudyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        btn_button.setOnClickListener {
            //            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
//            finish()

//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)

//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")
//            startActivity(intent)


//            val data = "Hello Kotlin"
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("extra_data", data)
////            startActivity(intent)
//            startActivityForResult(intent, 1)

            val contentOne = etContentOne.text.toString().trim()
            val contentTwo = etContentTwo.text.toString().trim()
            if (contentOne.isEmpty()||contentTwo.isEmpty()){
                Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show()
            }else{
                SecondActivity.actionStart(this, contentOne, contentTwo)
            }
        }
    }

    /**
     * 创建系统Menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_study, menu)
        return true
    }

    /**
     * 系统Menu点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val stringExtra = data?.getStringExtra("data_return")
                Toast.makeText(this, "接收传递数据: $stringExtra", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
