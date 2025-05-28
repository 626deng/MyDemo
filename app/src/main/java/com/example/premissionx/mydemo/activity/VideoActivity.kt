package com.example.premissionx.mydemo.activity

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.premissionx.mydemo.MyApplication
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.Utils
import com.example.premissionx.mydemo.adapter.VideoAdapter
import com.example.premissionx.mydemo.data.VideoResponse
import com.example.premissionx.mydemo.network.Okhttp
import com.google.gson.Gson
import kotlinx.coroutines.newFixedThreadPoolContext
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.lang.ref.WeakReference
import javax.security.auth.callback.Callback

class VideoActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: VideoAdapter
    val str="hot_video"

    val handler=object:Handler(Looper.myLooper()!!){
        val weakReference=WeakReference<Activity>(this@VideoActivity)
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity=weakReference.get()
            activity?.let {
                if(msg.what==100)
                {
                    val datas=msg.obj.toString()
                    Utils.d("VideoActivity",datas)
                    val videos=Gson().fromJson(datas,VideoResponse::class.java)
                    val lists=videos.result
                    Utils.d("VideoActivity",lists.toString())
                    adapter=VideoAdapter(this@VideoActivity,lists)
                    recyclerView.adapter=adapter
                }
            }
        }
    }

    val handler2=object:Handler(Looper.myLooper()!!){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what==100)
            {
                val datas=msg.obj.toString()
                Utils.d("VideoActivity",datas)
                val videos=Gson().fromJson(datas,VideoResponse::class.java)
                val lists=videos.result
                Utils.d("VideoActivity",lists.toString())
                adapter=VideoAdapter(this@VideoActivity,lists)
                recyclerView.adapter=adapter
            }
        }
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        getHttpData()
    }

    fun getHttpData()
    {
        Okhttp.getCall2(str).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                Utils.d("VideoActivity","77777777777777777777777777")
            }
            override fun onResponse(call: Call, response: Response) {
                val datas=response.body?.string()
                val message=Message()
                message.obj=datas
                message.what=100
                handler.sendMessage(message)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}