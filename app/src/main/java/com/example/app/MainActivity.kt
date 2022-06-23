package com.example.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.app.entity.User
import com.example.app.widget.CodeView
import com.example.core.utils.CacheUtils
import com.example.core.utils.toast
import com.example.lesson.LessonActivity

class MainActivity: AppCompatActivity(), View.OnClickListener{
    val usernameKey = "username"
    val passwordKey = "password"
    /**
     * lateinit:用了，说明在使用变量时，变量已经初始化，否则就会报错。
     * 强行调用 !! 安全调用 ?
     * **/
    private lateinit var et_username: EditText
    private lateinit var et_password: EditText
    private lateinit var et_code: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_code = findViewById(R.id.et_code)

        et_username.setText(CacheUtils.get(usernameKey))
        et_password.setText(CacheUtils.get(passwordKey))
        /**
         * 用了 Kotlin，不一定就完全摆脱空指针异常了。
         * 平台类型：如 Button，是安卓自带的，Kotlin 不能判断是不是为空。
         * 在 Java 里加合适的注解注解 @Nullable @NonNull，Kotlin 就可以识别了。
         * **/
        val btn_login = findViewById<Button>(R.id.btn_login)
        val img_code = findViewById<CodeView>(R.id.code_view)
        btn_login.setOnClickListener(this)
        img_code.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        /**
         * Kotlin 用 as 强转。
         * Kotlin 可以进行类型推断，不需要强转。
         * **/
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }
    }

    private fun login() {
        val username = et_username.text.toString()
        val password = et_password.text.toString()
        val code = et_code.text.toString()
        val user = User(username, password, code)
        if (verify(user)) {
            CacheUtils.save(usernameKey, username)
            CacheUtils.save(passwordKey, password)
            /**
             * 使用 类名::class.java 获取 Class
             * **/
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    private fun verify(user: User): Boolean {
        val username = user.username
        if (username != null && username.length < 4) {
            toast("用户名不合法")
            return false
        }
        if (user.password != null && user.password.length < 4) {
            toast("密码不合法")
            return false
        }
        return true
    }

}