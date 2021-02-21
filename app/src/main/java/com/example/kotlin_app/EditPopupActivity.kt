package com.example.kotlin_app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_popup.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EditPopupActivity : AppCompatActivity() {

    internal lateinit var db: DBHelper
    internal var doits: List<Do_it> = ArrayList<Do_it>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_edit_popup)

        val it_edit: Intent = intent
        val e_title: String? = it_edit.getStringExtra("e_title")
        val chk_title: String? = e_title
        if(e_title != null){
            edit_doit.setText(e_title)
        }

        db = DBHelper(this)

        var now = System.currentTimeMillis()
        var mDate : Date = Date(now);
        var sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd");
        var getTime : String = sdf.format(now)

        btn_edit_confirm.setOnClickListener{
            if(e_title.equals(null)) {
                if (edit_doit.text.toString().equals("")) {
                    Toast.makeText(applicationContext, "할 일 항목을 입력해주세요.", Toast.LENGTH_LONG).show()
                } else {
                    val Do_it = Do_it()
                    Do_it.title = edit_doit.text.toString()
                    Do_it.date = getTime

                    db.addDoit(Do_it)
                    finish()
                }
            } else{
                if (edit_doit.text.toString().equals("")) {
                    Toast.makeText(applicationContext, "할 일 항목을 입력해주세요.", Toast.LENGTH_LONG).show()
                } else {
                    val Do_it = Do_it()
                    Do_it.title = edit_doit.text.toString()
                    Do_it.date = getTime

                    db.updateDoit(Do_it, chk_title!!)
                    finish()
                }
            }
        }
    }
}