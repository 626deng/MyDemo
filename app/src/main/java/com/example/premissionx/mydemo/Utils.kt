package com.example.premissionx.mydemo

import android.util.Log

object Utils {
    private const val a=1
    private const val b=2
    private const val c=3
    private  const val d=4
    private const val e=5
    var  h=a
    fun e(tsg:String,msg:String){
        if(h<=a)
        {
            Log.e(tsg,msg)
        }
    }
    fun w(tsg:String,msg:String){
        if(h<=b)
        {
            Log.w(tsg,msg)
                    }
    }
    fun d(tsg:String,msg:String){
        if(h<=c)
        {
            Log.d(tsg,msg)
        }
    }
    fun v(tsg:String,msg:String){
        if(h<=d)
        {
            Log.v(tsg,msg)
        }
    }
    fun i(tsg:String,msg:String){
        if(h<=e)
        {
            Log.i(tsg,msg)
        }
    }

}