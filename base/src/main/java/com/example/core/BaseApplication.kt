package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        currentApplication = this
    }

    companion object {
        /**
         * base application 必须由系统创建，不能使用 object
         * 此时可以使用伴生对象 companion object 来声明静态变量和方法
         * 在 Java 中，可以使用 BaseApplication.Companion.xxx 来调用
         * 用 @JvmStatic 修饰,就变成了真正的静态函数了
         * **/
        private var currentApplication: Context? = null
        fun currentApplication(): Context {
            return currentApplication!!
        }
    }
}