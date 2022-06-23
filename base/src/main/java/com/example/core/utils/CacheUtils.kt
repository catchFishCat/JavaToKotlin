package com.example.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.example.core.BaseApplication
import com.example.core.R

@SuppressLint("StaticFieldLeak")
object CacheUtils {
    /**
     * Object 实际上是定义了一个类的单例对象
     * 在 Java 中需要用 CacheUtils.Instance.xxx 进行调用
     * **/
    val context = BaseApplication.currentApplication()


    val SP =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun save(key: String?, value: String?) {
        SP.edit().putString(key, value).apply()
    }

    operator fun get(key: String?): String? {
        return SP.getString(key, null)
    }
}