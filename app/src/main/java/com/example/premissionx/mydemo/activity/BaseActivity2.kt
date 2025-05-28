package com.example.premissionx.mydemo.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.premissionx.mydemo.ActivityCollector
import com.example.premissionx.mydemo.Utils

open class BaseActivity2:BaseActivity(){

    lateinit var receiver: MyReceiver
    lateinit var intentFilter:IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)

    }

    override fun onResume() {
        super.onResume()
        Utils.d("BaseActivity2","zzzzzzzzzzzzzzzzzzzz")
        intentFilter=IntentFilter()
        intentFilter.addAction("com.broadcast")
        receiver=MyReceiver()
        registerReceiver(receiver,intentFilter)
        Utils.d("BaseActivity2","hsifhoiefoihufhsauigfushyfuuofew")
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class MyReceiver:BroadcastReceiver(){
        override fun onReceive(p0: Context?,p1: Intent?) {
            AlertDialog.Builder(p0!!).apply {
                setTitle("This is title")
                setMessage("你已被强制退出")
                setCancelable(false)
                setPositiveButton("OK")
                { _,_->
                    ActivityCollector.finishAllActivities()
                    val intent=Intent(p0,LoginActivity::class.java)
                    p0.startActivity(intent)
                }
            }
        }
    }
}