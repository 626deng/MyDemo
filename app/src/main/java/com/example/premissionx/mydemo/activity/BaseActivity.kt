package com.example.premissionx.mydemo.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity:AppCompatActivity(){

    protected open fun initDatum(){}
    protected open fun initViews(){}
    protected open fun initListeners(){}

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initDatum()
        initViews()
        initListeners()
    }

    fun A()
    {
        Log.d("a","aa")
    }

}