package com.example.premissionx.mydemo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication:Application() {
    companion object{
        var url="https://v.juhe.cn/toutiao/index?key=67f395a0ed780bc77e5ba33606f282d9&type="
        var url2="https://apis.juhe.cn/fapig/douyin/billboard?key=85cf587e9c755d3da879c6255d9ef04d&type="
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}