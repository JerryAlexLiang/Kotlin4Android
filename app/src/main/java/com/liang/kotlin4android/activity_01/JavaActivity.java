package com.liang.kotlin4android.activity_01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.liang.kotlin4android.R;
import com.liang.kotlin4android.grammar.KotlinStaticUtil;

public class JavaActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        //doAction2()如果没有加上@JvmStatic注解的话，在Java类中找不到对应的"静态方法"
        KotlinStaticUtil.doAction2(this);
    }
}