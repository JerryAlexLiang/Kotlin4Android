package com.liang.kotlin4android.activity_01

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.liang.kotlin4android.BaseActivity
import com.liang.kotlin4android.R
import com.liang.kotlin4android.utils.ActivityBox
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {

    private val tag = SecondActivity::class.java.simpleName

    companion object {

//        fun actionStart(context: Context, parameter: String) {
//            val intent = Intent(context, SecondActivity::class.java)
//            intent.putExtra("extra_data", parameter)
//            context.startActivity(intent)
//        }

        fun actionStart(context: Context, parameter: String,parameter2: String){
            val intent = Intent(context,SecondActivity::class.java).apply {
                putExtra("extra_data", parameter)
                putExtra("extra_data2", parameter2)
            }
//            context.startActivity(intent)
            val activity = context as Activity
            activity.startActivityForResult(intent,1)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d(tag,"执行onCreate")

        //接收传递来的数据
        val stringExtra = intent.getStringExtra("extra_data")
        val stringExtra2 = intent.getStringExtra("extra_data2")
//        Toast.makeText(this, "接收数据: $stringExtra", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "接收数据: $stringExtra love $stringExtra2", Toast.LENGTH_SHORT).show()

        btnJump.setOnClickListener {
            val toString = et_content.text.toString()
            intent.putExtra("data_return", toString)
            if (toString.isEmpty()) {
                Toast.makeText(this, "请先输入回传内容", Toast.LENGTH_SHORT).show()
            } else {
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

        btnExitApp.setOnClickListener {
            ActivityBox.finishAll()
        }
    }

//    override fun onBackPressed() {
//        val toString = et_content.text.toString()
//        intent.putExtra("data_return", toString)
//        if (toString.isEmpty()) {
//            Toast.makeText(this, "请先输入回传内容", Toast.LENGTH_SHORT).show()
//        } else {
//            setResult(Activity.RESULT_OK, intent)
//            finish()
//        }
//    }

    override fun onStart() {
        super.onStart()
        Log.d(tag,"执行onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag,"执行onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag,"执行onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"执行onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag,"执行onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag,"执行onRestart()")
    }

}
