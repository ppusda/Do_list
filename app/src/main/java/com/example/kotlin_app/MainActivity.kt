package com.example.kotlin_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ExpandableListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val header : MutableList<String> = ArrayList()
    val body : MutableList<MutableList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_edit.setOnClickListener {
            btn_edit.visibility = View.GONE;
            btn_set.visibility = View.GONE;
            btn_add.visibility = View.VISIBLE;
            btn_del.visibility = View.VISIBLE;
            btn_back.visibility = View.VISIBLE;
        }

        btn_back.setOnClickListener {
            btn_edit.visibility = View.VISIBLE;
            btn_set.visibility = View.VISIBLE;
            btn_add.visibility = View.GONE;
            btn_del.visibility = View.GONE;
            btn_back.visibility = View.GONE;
        }

        btn_add.setOnClickListener {
            val season1 : MutableList<String> = ArrayList()
            season1.add("finally")
            season1.add("i did it!")

            header.add("this is my first expandalbeList")
            body.add(season1)

            list_doit.setAdapter(Do_list_Adapter(this, list_doit, header, body))
        }

        btn_del.setOnClickListener {

        }

        btn_set.setOnClickListener {
            val it_set = Intent(this, SettingActivity::class.java)
            startActivity(it_set);
        }
    }
}