package com.example.kotlin_app

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DBHelper (context: Context) : SQLiteOpenHelper(context, Database_name, null, Database_ver){

    companion object {
        private val Database_ver = 1
        private val Database_name = "doapp.db"

        //table
        private val Table_name = "do_it"
        private val col_title = "title"
        private val col_date = "date"

        private val f_table_name = "f_do"
        private val col_f_title = "f_title"
        private val col_a_date = "a_date"
        private val col_f_date = "f_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val Create_table_query = ("Create Table if not exists " +
                "$Table_name ($col_title TEXT PRIMARY KEY," +
                "$col_date TEXT);" )
        db!!.execSQL(Create_table_query)

        val Create_ftable_query = ("Create Table if not exists " +
                "${f_table_name} (${col_f_title} TEXT PRIMARY KEY, " +
                "${col_a_date} TEXT, ${col_f_date} TEXT);" )
        db!!.execSQL(Create_ftable_query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop Table if exists $Table_name")
        onCreate(db!!)

        db!!.execSQL("Drop Table if exists $f_table_name")
        onCreate(db!!)
    }

    fun addDoit(do_it: Do_it){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(col_title, do_it.title)
        values.put(col_date, do_it.date)

        db.insert(Table_name, null, values)
        db.close()
    }

    fun updateDoit(do_it: Do_it, chk_title: String){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(col_title, do_it.title)
        values.put(col_date, do_it.date)

        db.update(Table_name, values, "$col_title=?", arrayOf(chk_title))
    }

    fun deleteDoit(do_it: Do_it){
        val db = this.writableDatabase

        db.delete(Table_name, "$col_title=?", arrayOf(do_it.title.toString()))
        db.close()
    }

    fun addF_Doit(do_it: Do_it){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(col_f_title, do_it.title)
        values.put(col_a_date, do_it.date)
        values.put(col_f_date, do_it.f_date)

        db.insert(f_table_name, null, values)
        db.close()
    }

    fun updateF_Doit(do_it: Do_it, chk_title: String){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(col_f_title, do_it.title)
        values.put(col_a_date, do_it.date)
        values.put(col_f_date, do_it.f_date)

        db.update(f_table_name, values, "${col_f_title}=?", arrayOf(chk_title))
    }

    fun deleteF_Doit(do_it: Do_it){
        val db = this.writableDatabase

        db.delete(f_table_name, "${col_f_title}=?", arrayOf(do_it.title.toString()))
        db.close()
    }
}