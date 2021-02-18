package com.example.kotlin_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast

class Do_list_Adapter(var context: Context, var expandableListView: ExpandableListView,
                      var header: ArrayList<String>, var body: ArrayList<ArrayList<String>>) : BaseExpandableListAdapter() {

    // Group
    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun getGroupCount(): Int {
        return header.size
    }
    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()

    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            var infalter = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalter.inflate(R.layout.do_list_parent, null)
        }
        val title = convertView?.findViewById<TextView>(R.id.txt_list_Title)
        title?.text = getGroup(groupPosition)
        title?.setOnClickListener {
            if(expandableListView.isGroupExpanded(groupPosition)){
                expandableListView.collapseGroup(groupPosition)
            } else{
                expandableListView.expandGroup(groupPosition)
            }
        }
        return convertView
    }

    // Child

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return body[groupPosition][childPosition]
    }
    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            var infalter = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalter.inflate(R.layout.do_list_chlid, null)
        }
        val title = convertView?.findViewById<TextView>(R.id.txt_list_Content)
        title?.text = getChild(groupPosition, childPosition)
        title?.setOnClickListener {

        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }

    fun refreshItems(listDataHeader: ArrayList<String>, listChildData: ArrayList<ArrayList<String>>) {
        this.header = listDataHeader
        this.body = listChildData
        notifyDataSetChanged()
    }

}