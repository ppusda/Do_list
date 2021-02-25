package com.example.kotlin_app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_app_info.*

class AppInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val chk_ver = "현재 버전 : " + BuildConfig.VERSION_NAME

        txt_now_version.text = chk_ver
        txt_recent_version.text = "최신 버전 : 1.0"

        btn_info_confirm.setOnClickListener {
            finish()
        }
    }
}