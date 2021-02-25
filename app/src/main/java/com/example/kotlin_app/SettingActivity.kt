package com.example.kotlin_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        ll_app_info.setOnClickListener {
            val it_set = Intent(applicationContext, AppInfoActivity::class.java)
            startActivity(it_set)
        }

        ll_finish_list.setOnClickListener{
            val it_set = Intent(applicationContext, FinishListActivity::class.java)
            startActivity(it_set)
        }

    }
}