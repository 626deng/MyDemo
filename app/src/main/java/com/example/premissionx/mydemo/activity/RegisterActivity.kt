package com.example.premissionx.mydemo.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.database.MyDatabaseHelper

class RegisterActivity : BaseActivity2() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val button2=findViewById<Button>(R.id.button2)
        
        button2.setOnClickListener{
            val editText1=findViewById<EditText>(R.id.editText1)
            val editText2=findViewById<EditText>(R.id.editText2)
            val account=editText1.text.toString()
            val password=editText2.text.toString()
            if(account.length>=8&&password.length>=8)
            {
                val helper=MyDatabaseHelper(this,"User.db",2)
                val db=helper.writableDatabase
                val values=ContentValues().apply {
                    put("account",account)
                    put("password",password)
                }
                db.insert("User",null,values)
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT ).show()
                val intent=Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }
           else  if(account.length>=8)
            {
                Toast.makeText(this,"密码位数需要大于8位",Toast.LENGTH_SHORT ).show()
            }
            else
            {
                Toast.makeText(this,"账号位数需要大于八位",Toast.LENGTH_SHORT ).show()
            }
        }
    }
}