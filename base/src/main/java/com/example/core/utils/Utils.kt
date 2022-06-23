package com.example.core.utils
/**
 * 用 @file:JvmName("xxx")，对在 java 中的调用进行重命名
 * **/
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication

private val displayMetrics = Resources.getSystem().displayMetrics

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

fun toast(string: String?) {
    toast(string, Toast.LENGTH_SHORT)
}

fun toast(string: String?, duration: Int) {
    Toast.makeText(BaseApplication.currentApplication(), string, duration).show()
}