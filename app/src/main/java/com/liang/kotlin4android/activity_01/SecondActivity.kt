package com.liang.kotlin4android.activity_01

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.liang.kotlin4android.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

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
}
