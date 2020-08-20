package com.liang.kotlin4android.activity_01

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
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
import com.liang.kotlin4android.grammar.appendStartsApply
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

        btnKotlinJavaStatic.setOnClickListener {
            JavaActivity.actionStart(this)
        }

        /**
         * Kotlin中的静态方法2
         * 顶层方法：kotlin编译器会将所有的顶层方法全部编译成静态方法
         * 在kotlin代码中，所有的顶层方法都可以在任何位置被直接调用，不用管包名路径，也不用创建实例，直接键入fun方法即可
         */
        val list = listOf("丁程鑫", "马嘉祺", "贺峻霖", "刘耀文", "敖子逸", "李天泽")
        appendStartsApply(list)
    }

    override fun isSetTransparencyBar(): Boolean {
        return false
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
        //返回true表示允许创建的菜单显示出来，如果返回false，则创建的菜单将无法显示
        return true
    }

    /**
     * 系统Menu点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> {
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()

                //kotlin中的标准函数run
                val dialog = androidx.appcompat.app.AlertDialog.Builder(this).run {
                    setTitle("系统Menu")
                    setMessage("增加选项1")
                    setCancelable(false)
                    setPositiveButton("确定") { dialog, which ->
                        dialog.dismiss()
                        //在给EditText赋值时使用editText.text = "value"时会提示Type mismatch，这是因为EditText在推断types时出现问题;
                        //为了避免types不匹配，使用Editable类的Factory内部类，来解决这个问题
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

//                val dialog = androidx.appcompat.app.AlertDialog.Builder(this).run {
//                    setTitle("系统Menu")
//                    setMessage("增加选项2")
//                    setCancelable(false)
//                    setPositiveButton("确定") { dialog, which ->
//                        dialog.dismiss()
//                        etContentTwo.text = Editable.Factory.getInstance().newEditable("马嘉琪")
//                    }
//                    setNegativeButton("取消") { dialog, which ->
//                        dialog.dismiss()
//                    }
//                    create()
//                }
//                dialog.show()

                //kotlin中的标准函数apply
                AlertDialog.Builder(this).apply {
                    setTitle("系统Menu")
                    setMessage("增加选项2")
                    setCancelable(false)
                    setPositiveButton("确定") { dialog, _ ->
                        dialog.dismiss()
                        etContentTwo.text = Editable.Factory.getInstance().newEditable("马嘉琪")
                    }
                    setNegativeButton("取消") { dialog, _ ->
                        dialog.dismiss()
                    }
                    show()
                }
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
