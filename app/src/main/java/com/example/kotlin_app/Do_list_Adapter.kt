package com.example.kotlin_app

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class Do_list_Adapter : BaseAdapter() {

    var ListViewItemList = ArrayList<Do_it>()

    override fun getCount(): Int {
        return ListViewItemList.size
    }

    override fun getItem(position: Int): Any {
        return ListViewItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var context = parent?.context

        if(view == null) {
            val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.do_list, parent, false)
        }

        val titleTextView = view?.findViewById<TextView>(R.id.txt_list_Title)
        val listViewItem = ListViewItemList[position]

        titleTextView?.setText(listViewItem.title)

        return view!!
    }

    fun addItem(title: String) {
        var item: Do_it = Do_it()
        item.title = title
        ListViewItemList.add(item)
    }

    fun ClearItem() {
        ListViewItemList.clear()
    }


}