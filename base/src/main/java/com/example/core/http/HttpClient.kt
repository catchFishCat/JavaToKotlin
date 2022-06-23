package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {
    private val gson = Gson()

    private fun <T> convert(json: String?, type: Type?): T {
        return gson.fromJson(json, type)
    }

    fun <T> get(path: String, type: Type?, entityCallback: EntityCallback<T>) {
        val request = Request.Builder()
            /**用 $变量名 来加入参数**/
            .url("https://api.hencoder.com/$path")
            .build()
        val call: Call = HttpClient.newCall(request)
        /**利用 objet 关键字创建匿名内部类**/
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
            }

            override fun onResponse(call: Call, response: Response) {
                /**
                 * 用 when 代替 if 和 switch
                 * 用 a..b 代替 <= >=，提高代码可读性
                 * **/

                when (response.code()) {
                    in 200..299 ->
                        /**
                         * Kotlin 没有强制要求 try...catch
                         * 如果却不会出错即可抛弃代码块
                         * **/
                        entityCallback.onSuccess(convert<Any>(response.body()!!.string(), type) as T)
                    in 400..499 -> entityCallback.onFailure("客户端错误")
                    in 500..599 -> entityCallback.onFailure("服务器错误")
                    else -> entityCallback.onFailure("未知错误")

                }
            }
        })
    }

}