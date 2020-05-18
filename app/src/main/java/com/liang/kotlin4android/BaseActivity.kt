package com.liang.kotlin4android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.liang.kotlin4android.utils.ActivityBox

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity",javaClass.simpleName)
        ActivityBox.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityBox.removeActivity(this)
    }
}
