package com.example.kotlin_app

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chkfinish.*
import java.text.SimpleDateFormat
import java.util.*

class ChkfinishActivity : AppCompatActivity() {
    lateinit var database: SQLiteDatabase
    lateinit var db: DBHelper
    lateinit var f_db: F_DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_chkfinish)

        f_db = F_DBHelper(this)
        database = f_db.writableDatabase

        var now = System.currentTimeMillis()
        var mDate : Date = Date(now);
        var sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd");
        var getTime : String = sdf.format(now)

        val it_chk = intent
        var e_title = it_chk.getStringExtra("e_title")
        var e_date = it_chk.getStringExtra("e_date")

        btn_chkf_confirm.setOnClickListener {
            val f_Do_it = Do_it()
            f_Do_it.title = e_title
            f_Do_it.date = e_date
            f_Do_it.f_date = getTime
            f_db.addF_Doit(f_Do_it)

            val Do_it = Do_it()
            Do_it.title = e_title
            db.deleteDoit(Do_it)

            Toast.makeText(this, "수고하셨습니다!", Toast.LENGTH_LONG).show()
            finish()
        }

        btn_chkf_cancel.setOnClickListener {
            finish()
        }
    }
}