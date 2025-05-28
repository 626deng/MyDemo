package com.example.premissionx.mydemo.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class MyDatabaseHelper(val
                       context:Context,name:String,version:Int):SQLiteOpenHelper(context,name,null,version){
    val sql="create table User(" +
            "id integer  primary key autoincrement," +
            "account text," +
            "password text" +
            ")"

    val sql2="create table News(" +
            "id integer primary key autoincrement," +
            "uniqueKey text," +
            "url text," +
            "text1 text," +
            "text2 text," +
            "date text," +
            "pic_url text" +
            ")"
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(sql)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
           if(p1<2)
           {
                p0?.execSQL(sql2)
           }
    }
}