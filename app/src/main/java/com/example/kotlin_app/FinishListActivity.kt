package com.example.kotlin_app

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_finish_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FinishListActivity : AppCompatActivity() {

    lateinit var callbackMethod : DatePickerDialog.OnDateSetListener
    lateinit var database: SQLiteDatabase
    lateinit var f_db: DBHelper

    lateinit var ft_arr: ArrayList<String>
    lateinit var ad_arr: ArrayList<String>
    val adapter: Finish_list_Adapter = Finish_list_Adapter()
    var mid_date: String = ""
    var chk: Int = 1
    var f_chk: Int = 0

    lateinit var now: Any
    lateinit var sel_now: Any
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_list)

        f_db = DBHelper(this)
        database = f_db.writableDatabase
        list_finish.adapter = adapter

        var now = System.currentTimeMillis()
        gs_date(now)

        this.DatePickerListener()

        var chk_date = txt_selDate.text.split("/")

        btn_selDate.setOnClickListener {
            var dialog: DatePickerDialog = DatePickerDialog(this, callbackMethod, chk_date[0].toInt(), chk_date[1].toInt() - 1, chk_date[2].toInt())
            dialog.show()
            refreshData(mid_date)
        }

        btn_beforeDate.setOnClickListener {
            if(chk == 1){
                now -= (1000 * 60 * 60 * 24)
                gs_date(now)
            }else{
                var sdf = SimpleDateFormat("yyyy/MM/dd")
                var date: Date = sdf.parse(mid_date)
                var sel_now = date.time
                sel_now -= (1000 * 60 * 60 * 24)
                gs_date(sel_now)
            }
            refreshData(mid_date)
        }

        btn_nextDate.setOnClickListener {
            if(chk == 1){
                now += (1000 * 60 * 60 * 24)
                gs_date(now)
            }else{
                var sdf = SimpleDateFormat("yyyy/MM/dd")
                var date: Date = sdf.parse(mid_date)
                var sel_now = date.time
                sel_now += (1000 * 60 * 60 * 24)
                gs_date(sel_now)
            }
            refreshData(mid_date)
        }

        list_finish.setOnItemClickListener { parent, view, position, id ->
            val f_title = adapter.ListViewItemList[position].title

            val it_sel = Intent(applicationContext, SelectPopupActivity::class.java)
            it_sel.putExtra("e_title", f_title)
            it_sel.putExtra("chk", f_chk)
            startActivity(it_sel)
        }
    }

    private fun DatePickerListener(){
        callbackMethod = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            if(month < 9){
                if(dayOfMonth < 10){
                    mid_date = "$year/0${month+1}/0$dayOfMonth"
                    txt_selDate.text = mid_date
                }else{
                    mid_date = "$year/0${month+1}/$dayOfMonth"
                    txt_selDate.text = mid_date
                }
            }else{
                if(dayOfMonth < 10){
                    mid_date = "$year/${month+1}/0$dayOfMonth"
                    txt_selDate.text = mid_date
                }else{
                    mid_date = "$year/${month+1}/$dayOfMonth"
                    txt_selDate.text = mid_date
                }
            }
            chk = 0
            refreshData(mid_date)
        }
    }

    fun gs_date(now: Any){
        var date = SimpleDateFormat("yyyy/MM/dd")
        var getdate = date.format(now).toString()
        txt_selDate.text = getdate
        mid_date = getdate
    }

    private fun refreshData(sc_date: String) {
        val cursor: Cursor = database.rawQuery("SELECT * FROM f_do WHERE f_date = '$sc_date';", null)
        ft_arr = ArrayList<String>()
        ad_arr = ArrayList<String>()
        while(cursor.moveToNext()){
            ft_arr.add(cursor.getString(cursor.getColumnIndex("f_title")))
            ad_arr.add(cursor.getString(cursor.getColumnIndex("a_date")))
        }
        adapter.ClearItem()
        addData(ft_arr, ad_arr)

        var dla : Finish_list_Adapter = list_finish.adapter as Finish_list_Adapter
        dla.notifyDataSetChanged()
        list_finish.adapter = dla
    }

    private fun addData(ft_arr: ArrayList<String>, ad_arr: ArrayList<String>) {
        var t_itr: Iterator<String> = ft_arr.iterator()
        var d_itr: Iterator<String> = ad_arr.iterator()

        while(t_itr.hasNext()){
            adapter.addItem(t_itr.next(), d_itr.next())
        }
    }

    override fun onResume() {
        super.onResume()
        refreshData(mid_date)
    }

}