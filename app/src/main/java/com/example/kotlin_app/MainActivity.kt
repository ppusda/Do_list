package com.example.kotlin_app

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var database: SQLiteDatabase
    lateinit var db: DBHelper

    val adapter: Do_list_Adapter = Do_list_Adapter()
    lateinit var do_it: ArrayList<String>

    lateinit var t_arr: ArrayList<String>
    lateinit var d_arr: ArrayList<String>

    var chk : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)
        database = db.writableDatabase
        list_doit.adapter = adapter

        btn_main_edit.setOnClickListener {
            btn_main_edit.visibility = View.GONE;
            btn_set.visibility = View.GONE;
            btn_add.visibility = View.VISIBLE;
            btn_back.visibility = View.VISIBLE;
            chk *= -1
        }

        btn_back.setOnClickListener {
            btn_main_edit.visibility = View.VISIBLE;
            btn_set.visibility = View.VISIBLE;
            btn_add.visibility = View.GONE;
            btn_back.visibility = View.GONE;
            chk *= -1
        }

        btn_add.setOnClickListener {
            val it_set = Intent(this, EditPopupActivity::class.java)
            startActivity(it_set)
        }

        btn_set.setOnClickListener {
            val it_set = Intent(this, SettingActivity::class.java)
            startActivity(it_set)
        }

        list_doit.setOnItemClickListener { parent, view, position, id ->
            val e_title = adapter.ListViewItemList[position].title
            val e_date = adapter.ListViewItemList[position].date

            val it_sel = Intent(this, SelectPopupActivity::class.java)
            it_sel.putExtra("chk", chk)
            it_sel.putExtra("e_title", e_title)
            it_sel.putExtra("e_date", e_date)
            startActivity(it_sel)
        }

    }

    private fun refreshData() {
        val cursor: Cursor = database.rawQuery("SELECT * FROM do_it", null)
        var t_arr = ArrayList<String>()
        var d_arr = ArrayList<String>()
        while(cursor.moveToNext()){
            t_arr.add(cursor.getString(cursor.getColumnIndex("title")))
            d_arr.add(cursor.getString(cursor.getColumnIndex("date")))
        }
        adapter.ClearItem()
        addData(t_arr, d_arr)

        var dla : Do_list_Adapter = list_doit.adapter as Do_list_Adapter
        dla.notifyDataSetChanged()
        list_doit.adapter = dla
    }

    private fun addData(t_arr: ArrayList<String>, d_arr: ArrayList<String>) {
        var t_itr: Iterator<String> = t_arr.iterator()
        var d_itr: Iterator<String> = d_arr.iterator()
        while(t_itr.hasNext()){
            adapter.addItem(t_itr.next(), d_itr.next())
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }
}