package com.example.premissionx.mydemo.activity

import Data
import NewsResponse
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.premissionx.mydemo.ActivityCollector
import com.example.premissionx.mydemo.MyApplication
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.Utils
import com.example.premissionx.mydemo.adapter.NewsAdapter
import com.example.premissionx.mydemo.database.MyDatabaseHelper
import com.example.premissionx.mydemo.network.Okhttp
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.internal.userAgent
import java.io.IOException

@Suppress("UNREACHABLE_CODE")
class  MainActivity : BaseActivity2(){
//    lateinit var handler:Handler

    val str="top"
    var list= mutableListOf<Data>()
    lateinit var adapter:NewsAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var navigationView: NavigationView
//    lateinit var circleImageView: ImageView

    var handler=object :Handler(Looper.myLooper()!!){
        @SuppressLint("NotifyDataSetChanged")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what==100)
            {
                val str=msg.obj.toString()
                val datas=Gson().fromJson(str,NewsResponse::class.java)
                Log.d("MainActivity",datas.reason)
                if(datas.reason=="success!")
                {
                    val lists= datas.result.data
                    list.addAll(lists)
                    Utils.d("MainActivity",list.toString())
                    adapter= NewsAdapter(this@MainActivity,list)
                    recyclerView.adapter=adapter
                }
                else{
                    Utils.d("MainActivity","5555555555555555555555")
                }
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        circleImageView=findViewById(R.id.circleImageView)
        recyclerView=findViewById(R.id.recyclerView)
        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        getHttpData()

//        circleImageView.setOnClickListener {
//            val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
//            intent.type="image/*"
//            startActivityForResult(intent,1)
//        }

        navigationView=findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener{ item->
            when(item.itemId){
                R.id.navCall->
                {
                   val intent=Intent(this,RecordActivity::class.java)
                    startActivity(intent)
                }
                R.id.navFriends->
                {
                    val intent=Intent(this,VideoActivity::class.java)
                    startActivity(intent)
                }
                R.id.exit->{
                    ActivityCollector.finishAllActivities()
                    val intent=Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"你已退出登录",Toast.LENGTH_SHORT).show()
                }
                  R.id.exits->{
                    val intent=Intent("com.broadcast")
                    sendBroadcast(intent)
                }
                R.id.navTasks->{
                    val dpHelper=MyDatabaseHelper(this,"User.db",2)
                    val db=dpHelper.writableDatabase
                    val sql="delete from News where id>=11 and id <=18"
                    db.execSQL(sql)
                }
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            1->{
                if(requestCode==Activity.RESULT_OK&&data!=null)
                {
                    data.data?.let {
                        uri->
                        val bitmap=getBitmapFromUri(uri)
//                        circleImageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }



    fun getBitmapFromUri(uri:Uri):Bitmap
    {
        return contentResolver.openFileDescriptor(uri,"r").use {
            BitmapFactory.decodeFileDescriptor(it?.fileDescriptor)
        }
    }



    fun getHttpData() {
            Okhttp.getCall(MyApplication.url + str).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Utils.d("MainActivity", "888888888888888888888888")
                }
                override fun onResponse(call: Call, response: Response) {
                    val str = response.body?.string()
                    Utils.d("MainActivity", "222222222222222222")
                    val message = Message()
                    message.obj = str
                    message.what = 100
                    handler.sendMessage(message)
                }
            })
        }
}



