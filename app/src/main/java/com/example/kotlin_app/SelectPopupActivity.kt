package com.example.kotlin_app

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_popup.*

class SelectPopupActivity(): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_select_popup)

        val it_sel: Intent = intent
        var chk: Int = it_sel.getIntExtra("chk", 1)
        var e_title: String? = it_sel.getStringExtra("e_title")
        var e_date: String? = it_sel.getStringExtra("e_date")

        when(chk){
            0 -> {
                btn_sel_edit.visibility = View.GONE
                btn_finish.visibility = View.GONE
                btn_del.visibility = View.VISIBLE
            }
            -1 -> {
                btn_sel_edit.visibility = View.VISIBLE
                btn_finish.visibility = View.VISIBLE
                btn_del.visibility = View.VISIBLE
            }
            1 -> {
                btn_sel_edit.visibility = View.GONE
                btn_finish.visibility = View.VISIBLE
                btn_del.visibility = View.GONE
            }
        }

        btn_del.setOnClickListener {
            val it_chk = Intent(this, ChkdeleteActivity::class.java)
            it_chk.putExtra("e_title", e_title)
            if(chk == -1){
                it_chk.putExtra("chk", -1)
            }else if(chk == 0){
                it_chk.putExtra("chk", 0)
            }else{
                Log.d("Error", "error: selectpopup")
            }
            startActivity(it_chk)
            finish()
        }

        btn_sel_edit.setOnClickListener {
            val it_chk = Intent(this, EditPopupActivity::class.java)
            it_chk.putExtra("e_title", e_title)
            startActivity(it_chk)
            finish()
        }

        btn_finish.setOnClickListener {
            val it_chk = Intent(this, ChkfinishActivity::class.java)
            it_chk.putExtra("e_title", e_title)
            it_chk.putExtra("e_date", e_date)
            startActivity(it_chk)
            finish()
        }

        btn_sel_confirm.setOnClickListener {
            finish()
        }

    }
}