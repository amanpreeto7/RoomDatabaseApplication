package com.o7solutions.roomdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterClass(var arrayList: ArrayList<String>,var arrayDate: ArrayList<String>): BaseAdapter() {
    override fun getCount(): Int {
       return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return "Any"
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var initView = LayoutInflater.from(p2?.context).inflate(R.layout.item_adapter,p2,false)
        var tvName :TextView = initView.findViewById(R.id.tv_Task)
        var tvDate :TextView = initView.findViewById(R.id.tv_Date)
        tvName.setText(arrayList[p0])
        tvDate.setText(arrayDate[p0])
        return initView
    }
}