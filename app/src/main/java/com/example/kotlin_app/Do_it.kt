package com.example.kotlin_app

import android.provider.BaseColumns

class Do_it {
    var title : String = ""
    var date : String? = null
    var content : String? = null

    constructor(title: String, date: String, content: String){
        this.title = title
        this.date = date
        this.content = content
    }
}