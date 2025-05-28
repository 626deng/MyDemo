package com.example.premissionx.mydemo.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.adapter.RecordAdapter
import com.example.premissionx.mydemo.data.RecordData
import com.example.premissionx.mydemo.database.MyDatabaseHelper

class RecordActivity : AppCompatActivity() {

    lateinit var dbHelper:MyDatabaseHelper
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:RecordAdapter
    lateinit var data: RecordData
    var list= mutableListOf<RecordData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        dbHelper= MyDatabaseHelper(this,"User.db",2)

//        adapter=RecordAdapter(this,list)
//        recyclerView=findViewById(R.id.recyclerView)
//        val layoutManager= LinearLayoutManager(this)
//        recyclerView.layoutManager=layoutManager
//        recyclerView.adapter=adapter

        getData()
        adapter=RecordAdapter(this,list)
        recyclerView=findViewById(R.id.recyclerView)
        val layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=adapter
    }

    @SuppressLint("Range")
    fun getData()
    {
        val db=dbHelper.writableDatabase
        val cursor=db.query("News",null,null,null,null,null,null)
        if(cursor.moveToFirst())
        {
            do {
                val uniqueKey=cursor.getString(cursor.getColumnIndex("uniqueKey"))?:null
                val url=cursor.getString(cursor.getColumnIndex("url"))?:null
                val text1=cursor.getString(cursor.getColumnIndex("text1"))?:null
                val text2=cursor.getString(cursor.getColumnIndex("text2"))?:null
                val text3=cursor.getString(cursor.getColumnIndex("date"))?:null
//                val pic_text=cursor.getString(cursor.getColumnIndex("pic_text"))?:null
                data=RecordData(uniqueKey.toString(),url.toString(),text1.toString(),text2.toString(),text3.toString(),"NULL")
//                data.uniqueKey= uniqueKey.toString()
//                data.url= url.toString()
//                data.text1= text1.toString()
//                data.text2= text2.toString()
//                data.text3= text3.toString()
                if(data==null)
                Log.d("RecordActivity", "1111")
                else
                    Log.d("RecordActivity", data.toString())
//                if(pic_text!=null)
//                    data.pic_text= pic_text
//                else
//                    data.pic_text= null.toString()
                data?.let { list.add(it) }
                Log.d("RecordActivity", list.toString())
            }while (cursor.moveToNext())
        }
    }
}