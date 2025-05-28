package com.example.premissionx.mydemo.network

import com.example.premissionx.mydemo.MyApplication
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.suspendCoroutine

object Okhttp {
    fun getCall(url2:String):Call {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(MyApplication.url+url2).build()
        val call = okHttpClient.newCall(request)
        return call
    }

    fun getCall2(url2:String):Call {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(MyApplication.url2+url2).build()
        val call = okHttpClient.newCall(request)
        return call
    }



}
