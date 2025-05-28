package com.example.premissionx.mydemo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.premissionx.mydemo.R
import com.example.premissionx.mydemo.database.MyDatabaseHelper
class LoginActivity : BaseActivity2(){

    var a=1
    lateinit var button1:Button
    @SuppressLint("MissingInflatedId", "Range", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initDatum()
        initListeners()
//        button1=findViewById<Button>(R.id.button1)
//        button1.setOnClickListener {
//            val editText1=findViewById<EditText>(R.id.editText1)
//            val editText2=findViewById<EditText>(R.id.editText2)
//            val account= editText1.text.toString()
//            val password=editText2.text.toString()
//            val dbHelper=MyDatabaseHelper(this,"User.db",2)
//            val db=dbHelper.readableDatabase
//            val cursor=db.query("User",null,null,null,null,null,null)
//            if(cursor.moveToFirst())
//            {
//                do{
//                    val account1=cursor.getString(cursor.getColumnIndex("account"))
//                    val password1=cursor.getString(cursor.getColumnIndex("password"))
//                    if(account1==account&&password1==password)
//                    {
//                        val intent =Intent(this,MainActivity::class.java)
//                        startActivity(intent)
//                        a++
//                    }
//
//                }while(cursor.moveToNext())
//            }
//            if(a==1)
//            {
//                Toast.makeText(this,"账号或密码不正确",Toast.LENGTH_SHORT).show()
//            }
//            cursor.close()
//        }
    }

    override fun initDatum() {
        super.initDatum()
        button1=findViewById<Button>(R.id.button1)
    }

    override fun initViews() {
        super.initViews()
    }


    @SuppressLint("Range")
    override fun initListeners() {
        super.initListeners()
        button1.setOnClickListener {
            val editText1=findViewById<EditText>(R.id.editText1)
            val editText2=findViewById<EditText>(R.id.editText2)
            val account= editText1.text.toString()
            val password=editText2.text.toString()
            val dbHelper=MyDatabaseHelper(this,"User.db",2)
            val db=dbHelper.readableDatabase
            val cursor=db.query("User",null,null,null,null,null,null)
            if(cursor.moveToFirst())
            {
                do{
                    val account1=cursor.getString(cursor.getColumnIndex("account"))
                    val password1=cursor.getString(cursor.getColumnIndex("password"))
                    if(account1==account&&password1==password)
                    {
                        val intent =Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        a++
                    }

                }while(cursor.moveToNext())
            }
            if(a==1)
            {
                Toast.makeText(this,"账号或密码不正确",Toast.LENGTH_SHORT).show()
            }
            cursor.close()
        }
    }
}