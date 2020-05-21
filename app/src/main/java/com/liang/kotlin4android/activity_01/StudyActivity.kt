package com.liang.kotlin4android.activity_01

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_study.*

/**
 * 创建日期：2020/5/18 on 13:47
 * 描述: Activity的学习
 * 作者: liangyang
 */
class StudyActivity : BaseActivity(), View.OnClickListener {

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, StudyActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val tag = StudyActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        title = "Widget和Activity"

        Log.d(tag, "执行onCreate()")

        //Activity的生命周期
        if (savedInstanceState != null) {
            val tempData = savedInstanceState.getString("data_key")
            Log.d(tag, "还原数据: $tempData")
        }

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
            if (contentOne.isEmpty() || contentTwo.isEmpty()) {
                Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show()
            } else {
                SecondActivity.actionStart(this, contentOne, contentTwo)
            }
        }

        ivImage.tag = 1

        btnProgressBar.setOnClickListener(this)
        btnImageView.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnProgressBar -> {
                if (progressBar.progress < progressBar.max) {
                    progressBar.progress = progressBar.progress + 10
                    tvProgress.text = progressBar.progress.toString() + "%"
                } else {
                    progressBar.progress = 0
                    tvProgress.text = progressBar.progress.toString() + "%"
                }
            }

            R.id.btnImageView -> {
                if (ivImage.tag == 1) {
                    ivImage.setImageResource(R.drawable.img_2)
                    ivImage.tag = 2
                } else {
                    ivImage.setImageResource(R.drawable.img_1)
                    ivImage.tag = 1
                }
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
            R.id.add_item -> {
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()

                val dialog = androidx.appcompat.app.AlertDialog.Builder(this).run {
                    setTitle("系统Menu")
                    setMessage("增加选项1")
                    setCancelable(false)
                    setPositiveButton("确定") { dialog, which ->
                        dialog.dismiss()
                        etContentOne.text = Editable.Factory.getInstance().newEditable("丁程鑫")
                    }
                    setNegativeButton("取消") { dialog, which ->
                        dialog.dismiss()
                    }
                    create()
                }
                dialog.show()
            }
            R.id.remove_item -> {
                Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show()

                val dialog = androidx.appcompat.app.AlertDialog.Builder(this).run {
                    setTitle("系统Menu")
                    setMessage("增加选项2")
                    setCancelable(false)
                    setPositiveButton("确定") { dialog, which ->
                        dialog.dismiss()
                        etContentTwo.text = Editable.Factory.getInstance().newEditable("马嘉琪")
                    }
                    setNegativeButton("取消") { dialog, which ->
                        dialog.dismiss()
                    }
                    create()
                }
                dialog.show()
            }
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

    override fun onStart() {
        super.onStart()
        Log.d(tag, "执行onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "执行onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "执行onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "执行onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "执行onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "执行onRestart()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val tempData = "onSaveInstanceState"
        outState.putString("data_key", tempData)
        Log.d(tag, "执行onSaveInstanceState()")
    }

}
