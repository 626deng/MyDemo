package com.example.premissionx.mydemo.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.database.MyDatabaseHelper
class NewsDetailsActivity : BaseActivity2(){

    lateinit var webView:WebView
    lateinit var dbHelper: MyDatabaseHelper
    lateinit var key:String
    lateinit var url:String
    lateinit var text1:String
    lateinit var text2:String
    lateinit var text3:String
    lateinit var pic_url:String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        webView=findViewById(R.id.webView)

        dbHelper= MyDatabaseHelper(this,"User.db",2)
        key=intent.getStringExtra("uniqueKey").toString()
        url=intent.getStringExtra("url").toString()
        text1=intent.getStringExtra("text1").toString()
        text2=intent.getStringExtra("text2").toString()
        text3=intent.getStringExtra("text3").toString()
//        pic_url=intent.getStringExtra("pic_url").toString()
        webView.loadUrl(url)
        addHistoryRecord()

    }

    @SuppressLint("SuspiciousIndentation")
    fun addHistoryRecord()
    {
        val db=dbHelper.writableDatabase
        val values=ContentValues().apply {
            put("uniqueKey",key)
            put("url",url)
            put("text1",text1)
            put("text2",text2)
            put("date",text3)
//            put("pic_url",pic_url)
        }
        db.insert("News",null,values)
    }


}