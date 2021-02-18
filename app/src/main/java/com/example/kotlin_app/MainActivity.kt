package com.example.kotlin_app

import android.app.Person
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val header : ArrayList<String> = ArrayList()
    val body : ArrayList<ArrayList<String>> = ArrayList()

    lateinit var database: SQLiteDatabase
    lateinit var db: DBHelper

    internal var chk : Int = 0;

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        database = db.writableDatabase

        list_doit.setAdapter(Do_list_Adapter(this, list_doit, header, body))
        addData()

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
            val it_set = Intent(this, EditPopupActivity::class.java)
            startActivity(it_set);
        }

        btn_del.setOnClickListener {
        }

        btn_set.setOnClickListener {
            val it_set = Intent(this, SettingActivity::class.java)
            startActivity(it_set);
        }
    }

    private fun addData() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM do_it", null)
        while(cursor.moveToNext()){
            var do_it: ArrayList<String> = ArrayList()
            header.add(cursor.getString(cursor.getColumnIndex("title")))
            body.add(do_it)
            do_it.add(cursor.getString(cursor.getColumnIndex("content")))
        }

    }
}