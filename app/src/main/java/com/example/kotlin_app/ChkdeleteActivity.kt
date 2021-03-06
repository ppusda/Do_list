package com.example.kotlin_app

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chkdelete.*
import kotlinx.android.synthetic.main.activity_edit_popup.*
import kotlinx.android.synthetic.main.activity_main.*

class ChkdeleteActivity : AppCompatActivity() {

    lateinit var database: SQLiteDatabase
    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chkdelete)

        db = DBHelper(this)
        database = db.writableDatabase

        val it_chk: Intent = intent
        val e_title: String? = it_chk.getStringExtra("e_title")
        val chk: Int? = it_chk.getIntExtra("chk", 1)
        Log.d("e_title", "e_title: $e_title")
        Log.d("chk", "chk: $chk")

        btn_chk_cancel.setOnClickListener {
            finish()
        }

        btn_chk_confirm.setOnClickListener {
            if(chk == -1){
                if(e_title.equals("")){
                    Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    val Do_it = Do_it()
                    Do_it.title = e_title

                    db.deleteDoit(Do_it)
                    finish()
                }
            }else if(chk == 0){
                if(e_title.equals("")){
                    Toast.makeText(this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }else{
                    val Do_it = Do_it()
                    Do_it.title = e_title

                    db.deleteF_Doit(Do_it)
                    finish()
                }
            }else {
                Log.d("Error", "error : chkdelete")
            }

        }
    }
}