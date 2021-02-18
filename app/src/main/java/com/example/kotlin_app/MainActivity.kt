package com.example.kotlin_app

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var database: SQLiteDatabase
    lateinit var db: DBHelper

    val adapter: Do_list_Adapter = Do_list_Adapter()
    lateinit var do_it: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        database = db.writableDatabase

        list_doit.adapter = adapter


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

    private fun refreshData() : ArrayList<String> {
        val cursor: Cursor = database.rawQuery("SELECT * FROM do_it", null)
        var arrayList: ArrayList<String> = ArrayList()
        while(cursor.moveToNext()){
            Log.d("select", cursor.getString(cursor.getColumnIndex("title")))
            arrayList.add(cursor.getString(cursor.getColumnIndex("title")))
        }
        adapter.ClearItem()
        addData(arrayList)


        var dla : Do_list_Adapter = list_doit.adapter as Do_list_Adapter
        dla.notifyDataSetChanged()
        list_doit.adapter = dla

        return arrayList
    }

    private fun addData(arrayList: ArrayList<String>) {
        var itr: Iterator<String> = arrayList.iterator()
        while(itr.hasNext()){
            adapter.addItem(itr.next())
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }
}