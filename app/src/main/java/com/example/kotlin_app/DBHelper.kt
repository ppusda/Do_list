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
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val Create_table_query = ("Create Table if not exists " +
                "$Table_name ($col_title TEXT PRIMARY KEY," +
                "$col_date TEXT);" )
        db!!.execSQL(Create_table_query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop Table if exists $Table_name")
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

    fun updateDoit(do_it: Do_it):Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(col_title, do_it.title)
        values.put(col_date, do_it.date)

        return db.update(Table_name, values, "$col_title=?", arrayOf(do_it.title.toString()))
    }

    fun deleteDoit(do_it: Do_it){
        val db = this.writableDatabase

        db.delete(Table_name, "$col_title=?", arrayOf(do_it.title.toString()))
        db.close()
    }
}