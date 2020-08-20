package com.liang.kotlin4android

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val account = etAccount.text.toString()
            val password = etPassword.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "Jerry" && password == "123456") {
                startActivity<MainActivity>()
                finish()
            } else {
                toast("account or password is invalid")
            }

        }
    }

    override fun isSetTransparencyBar(): Boolean {
        return true
    }
}
